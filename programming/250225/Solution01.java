import java.util.*;

public class Solution01 {
    public static void main(String[] args) {
        // 스택 -> 쌓는다. -> 용 스택. 분노 스택. 풀 스택. 기술 스택. CS 스택은 쌓는 것
        // 쌓는 것은 무엇인가 -> 안전하게 빼려면 위에서부터 제거해야함. 최신부터 제거가 됨.
        // Stack -> 클래스. 이거 자체가 그 기능합니다. 후입선출 -> 실제 고객의 선택
        // 근데 심리게임 -> 그래서 뒤에 두기도 함. 즉, 중간에 가장 최신이 있게 됨
        Stack<Integer> stack = new Stack<>();
        stack.push(1); // push -> js. stack스러움.
        // js에서 stack은 별도 구현이 필요없음 (효율화를 위해선 필요한데...)
        stack.push(2);
        stack.push(3);
        System.out.println("stack = " + stack);
        // 가장 최근에 들어온 원소(요소)를 꺼내주는 기능
        System.out.println("stack.pop() = " + stack.pop()); // 3
        // 스택이 빌 때까지 꺼내겠다
        stack.push(4);
        stack.push(5);
        while (!stack.isEmpty()) { // true 동안 반복 -> not isEmpty하다면...
            System.out.println("stack.pop() = " + stack.pop());
        }
        // 자바에서의 스택 개념을 비전공자가 이해할 수 있게 중학생 수준에 맞춘 실생활 비유로 설명해줘

        // 큐 : 줄, 큐를 돌린다. -> 큐가 잡혀야... 큐. 큐는 줄
        // 먼저 들어온 사람이 먼저 가져간다. 선입선출. -> 편의점이 원하는 제품의 팔리는 순서
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // offer 제공
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        while (!queue.isEmpty()) {
            // 뽑기 -> poll -> 투표. poll.
            System.out.println("queue.poll() = " + queue.poll());
        }
        // 이게 왜? -> 내부에서 스택과 큐. 후입선출과 선입선출에 맞게 자료구조가 최적화
        // 엄청나게 복잡한 연산 혹은 짧은 시간이 필요할 경우, 이 최적화된 자료구조가
        // 시간 축소 혹은 메모리를 안정화에 도움을 준다.
        // 우리가 알고 있는 다른 자료구조 (배열, 리스트) -> 은근히 느리고 오래 걸림
        // String vs StringBuilder/Buffer 이정도 차이가 됨.
        // 1000회 정도 넘어가면? -> 성능 차이가 슬슬 벌어지고 '대용량 처리'하게 되면...
        // ms가 모여서 '분'이 된다. (병목, latency...)

        // 어 Stack은 Stack, Queue는 LinkedList? 맞아요.
        // 근데 그러면 미안하지만 '입문자'
        // 양방향으로 뚫려서 내 맘대로 반반 치킨 메뉴하는 자료구조 존재
        // Deque.
        Deque<Integer> deque = new ArrayDeque<>();
        // 현업?에서는 몰라도... 적어도 알고리즘 문제 풀 때 대부분 deque 씀.
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.offer(4);
        deque.offer(5);
        deque.offer(6);
        System.out.println("deque = " + deque);
        while (!deque.isEmpty()) {
            System.out.println("deque.pop() = " + deque.pop());
            System.out.println("deque.poll() = " + deque.poll());
        }
    }
}
