#!/bin/bash

success_count=0
fail_count=0

# 10,000번 반복 실행
for i in {1..10000}; do
  if (( RANDOM % 2 )); then
    # 50% 확률로 정상 엔드포인트 호출
    curl -s http://localhost:8080/counted > /dev/null
    ((success_count++))
    echo "[${i}/10000] Success: counted endpoint (Total: ${success_count})"
  else
    # 50% 확률로 에러 엔드포인트 호출
    curl -s http://localhost:8080/error > /dev/null
    ((fail_count++))
    echo "[${i}/10000] Failure: error endpoint (Total: ${fail_count})"
  fi
  sleep 1  # 1초 대기 (너무 빠른 요청 방지)
done

echo "Test complete: ${success_count} successes, ${fail_count} failures."

# 실제 메트릭이 생성되었는지 확인
echo "Checking Prometheus metrics..."
curl -s http://localhost:8080/actuator/prometheus | grep give_me_money_total