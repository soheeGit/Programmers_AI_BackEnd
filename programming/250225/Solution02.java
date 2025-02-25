import java.util.*;

public class Solution02 {
    public static void main(String[] args) {
        // 매개변수로 전달되는 인자에서는 {} 통한 배열 초기화가 불가능
        // new 타입[]을 붙여주거나 외부에서 초기화를 하고 나서 변수로 주입해야함.
        System.out.println(Arrays.toString(
//                solution(new int[]{93, 30, 55},
//                         new int[]{1, 30, 5})));
                solution2(new int[]{93, 30, 55},
                        new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(
//                solution(new int[]{95, 90, 99, 99, 80, 99},
//                         new int[]{1, 1, 1, 1, 1, 1})));
                solution2(new int[]{95, 90, 99, 99, 80, 99},
                        new int[]{1, 1, 1, 1, 1, 1})));
    }

    public static int[] solution2(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>(); // 리스트를 사용한 풀이
        Deque<Integer> queue = new ArrayDeque<>(); // 덱으로 큐 만들기
        // 1. 각 작업이 완료되는데 필요한 일수를 계산
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i]; // 100% 기준으로.
            double numberOfDaysRequired = (double) remainingProgress / speeds[i];
            queue.offer((int) Math.ceil(numberOfDaysRequired));
//            System.out.println("queue = " + queue);
        }
        System.out.println("queue = " + queue);
        // 2. 첫 번째 기능의 완료일수를 기준으로 시작
        int maxDay = queue.poll();
        int functionCount = 1;
        while (!queue.isEmpty()) {
            int day = queue.peek(); // peek은 제거하지 않고 값만 가져오는 것.
            // 앞에 직전에 있는 제1의 값을 그냥 둬야함.
            if (day <= maxDay) {
                queue.poll(); // maxDay보다 작거나 같을 때만 제거.
                functionCount++;
                continue; // poll하면서 줄어들었기 때문에...
            }
            answer.add(functionCount);
            maxDay = queue.poll(); //
            functionCount = 1;
        }

        answer.add(functionCount); // 마지막 거 남은 count를 무조건 넣어주기

        System.out.println("answer = " + answer);

        int[] result = new int[answer.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = answer.get(i); // answer에 있는 Intger로 감싸져있는 int를
            // result 배열에서 주입
        }
        return result;
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>(); // 리스트를 사용한 풀이
        // 1. 각 작업이 완료되는데 필요한 일수를 계산
        int[] days = new int[progresses.length]; // progresses.length -> 전체 기능의 개수
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i]; // 100% 기준으로.
            double numberOfDaysRequired = (double) remainingProgress / speeds[i];
            // 1.xxxx일 -> 실제로 며칠이 필요? -> 2일. 올림 (ceil) 내림 (floor)
            days[i] = (int) Math.ceil(numberOfDaysRequired); // 형 변환
            System.out.println("기능 " + i + ": 필요일수 " + days[i]);
        }
        // 2. 배포 순서가 필요, 만약에 뒤에 기능이 먼저/동일 완료 같이 배포.
        int maxDay = days[0]; // 최초 기능 배포일 (이것보다 작거나 같으면 뒤에 오는게 같이 배포)
        int functionCount = 1; // 기본적으로 1개는 배포가 됩니다.
        for (int i = 1; i < days.length; i++) { // 지금 0번째는 이미 카운트한 셈.
            // 맨 앞에 maxDay를 기준으로 이보다 작거나 같으면 functionCount 1 증가시킴.
            // 만약에 크다면? maxDay는 해당 day가 되고, functionCount는 1이 됨.
            // 다시 뒤에 있는 기능이 functionCount로 들어감
            if (days[i] <= maxDay) { // 현재 날짜가 이전 기능보다 짧거나 같게 걸린다면
                functionCount++;
                System.out.println("i = " + i);
                System.out.println("maxDay = " + maxDay);
                System.out.println("functionCount = " + functionCount);
                continue;
            } // else나 else if 연결하는 건 논리적으로 그렇게 좋은 선택이 아님.
            // days[i] > maxDay
            answer.add(functionCount); // 직전까지 배포 준비된 그룹을 발진!
            maxDay = days[i]; // 직전 최대 걸린 날짜를 현재 기능으로 대체
            functionCount = 1; // 현재 기능을 기준으로 다시 갯수 새는 것
            System.out.println("NEW GROUP");
        }
        answer.add(functionCount); // 마지막 그룹 (한 개가 무조건 남음)
        System.out.println("answer = " + answer);
        int[] result = new int[answer.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = answer.get(i); // answer에 있는 Intger로 감싸져있는 int를
            // result 배열에서 주입
        }
        return result;
    }
}
