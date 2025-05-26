#!/bin/bash

# --- 설정 ---
URL="http://localhost:8080/200"  # 테스트할 URL을 입력하세요.
TOTAL_REQUESTS=100000                           # 보낼 총 요청 수를 입력하세요.
CONCURRENT_USERS=5                           # 동시에 실행할 사용자(프로세스) 수를 입력하세요.
OUTPUT_FILE="load_test_results.log"           # 결과(HTTP 상태 코드)를 저장할 파일 이름
# ------------

# 변수 초기화
success_count=0
fail_count=0
request_count=0
pids=() # 백그라운드 프로세스 ID 저장 배열

# 이전 로그 파일 삭제 (선택 사항)
> "$OUTPUT_FILE"

echo "🚀 부하 테스트 시작..."
echo "-----------------------------------"
echo "대상 URL: $URL"
echo "총 요청 수: $TOTAL_REQUESTS"
echo "동시 사용자 수: $CONCURRENT_USERS"
echo "-----------------------------------"

start_time=$(date +%s)

# 요청을 보내는 함수
function send_request {
    local url=$1
    # curl: -s (silent), -o /dev/null (출력 버리기), -w "%{http_code}" (HTTP 상태 코드만 출력)
    http_code=$(curl -s -o /dev/null -w "%{http_code}" "$url")
    echo "$http_code" >> "$OUTPUT_FILE" # 파일에 상태 코드 기록
}

# 총 요청 수에 도달할 때까지 반복
while [ $request_count -lt $TOTAL_REQUESTS ]; do
    # 현재 실행 중인 백그라운드 작업 수 확인
    current_jobs=$(jobs -p | wc -l)

    # 설정된 동시 사용자 수보다 적게 실행 중이고, 총 요청 수에 도달하지 않았다면
    while [ $current_jobs -lt $CONCURRENT_USERS ] && [ $request_count -lt $TOTAL_REQUESTS ]; do
        send_request "$URL" & # 함수를 백그라운드로 실행
        pids+=($!)           # 백그라운드 프로세스 ID 저장
        request_count=$((request_count + 1))
        current_jobs=$(jobs -p | wc -l)
        # 진행 상황 표시 ( \r 을 사용하여 같은 줄에 덮어씀)
        echo -ne "요청 보냄: $request_count / $TOTAL_REQUESTS | 현재 동시 요청: $current_jobs \r"
    done

    # 동시 사용자 수에 도달했다면, 백그라운드 작업 중 하나가 끝날 때까지 기다림
    # -n 옵션: 실행 중인 작업 중 아무거나 하나가 종료되면 즉시 반환
    if [ $current_jobs -ge $CONCURRENT_USERS ]; then
        wait -n
    fi

    # 종료된 프로세스 ID 목록에서 제거 (선택 사항, 더 정확한 관리를 위해)
    # 실제로는 wait -n이 하나씩 처리하므로 이 부분은 단순화 가능
done

# 남아있는 모든 백그라운드 작업이 끝날 때까지 기다림
wait
echo -e "\n-----------------------------------" # 줄바꿈 후 구분선

end_time=$(date +%s)
duration=$((end_time - start_time))

echo "⏳ 모든 요청 완료. 결과 집계 중..."

# 결과 파일 분석
while read -r code; do
    if [[ $code -ge 200 && $code -lt 400 ]]; then
        success_count=$((success_count + 1))
    else
        fail_count=$((fail_count + 1))
    fi
done < "$OUTPUT_FILE"

echo "✅ 부하 테스트 종료."
echo "-----------------------------------"
echo "총 요청 수: $TOTAL_REQUESTS"
echo "성공 요청: $success_count"
echo "실패 요청: $fail_count"
echo "총 소요 시간: $duration 초"

if [ $duration -gt 0 ]; then
    # bc 계산기를 사용하여 초당 요청 수(RPS) 계산 (소수점 둘째 자리까지)
    rps=$(echo "scale=2; $TOTAL_REQUESTS / $duration" | bc)
    echo "초당 요청 수 (RPS): $rps"
else
    echo "소요 시간이 너무 짧아 RPS를 계산할 수 없습니다."
fi
echo "-----------------------------------"
echo "상세 결과(HTTP 상태 코드)는 $OUTPUT_FILE 파일을 확인하세요."