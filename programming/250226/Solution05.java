public class Solution05 {
    public static void main(String[] args) {
        // 재귀를 왜 알아야하죠?
        // DFS (아련...) 몇몇 상황에서는 Stack으로 안되는 경우 (백트래킹)
        // 재귀적 -> 나 자신을 아는 나 자신을 아는 나 자신을 아는 나 자신을...
        // 키워드만 좀 가져가시면 좋을 것 같다
        // 팩토리얼
        // 7! -> 7 x 6 x 5 x 4 x 3 x 2 x 1
        Factorial f = new Factorial();
//        System.out.println(f.factorialByIteration(20));
        System.out.println(f.factorialByRecursion(20));
    }
}

class Factorial {
    // 멤버변수에게 뭔가를 하기 좋음

    long factorialByIteration(int n) {
        // n -> n ~ ... ~ 1.
        long answer = 1;
        for (int i = n; i > 1; i--) {
            System.out.println("i = " + i);
            answer *= i; // 2까지 곱해짐
        }
        return answer;
    }

    long factorialByRecursion(int n) {
        // 스스로가 스스로를 호출하는 '재귀'
        // 멈추질 않아.
        if (n == 1) {
            return 1; // 더이상 자기 자신 호출하지 않는 '종료조건'
            // 일종의 반복 계열.
        }
        System.out.println("n = " + n);
        long next = factorialByRecursion(n - 1);
        System.out.println("next * n = " + next * n);
        // 스택과 같음. 재귀적 작업 = 스택
        // 근데... 재귀가 아니라 팩토리얼이라서 하는 이야기인데요... DP는 안알려주세요?
        // 동적 프로그래밍이 더 좋지 않아요? -> dynamic programming
        // 점화식으로 한다면 dp가 나와야하는 거 아니에요? -> 스택 -> 재귀 이해도 -> 재귀 -> dp.
        return next * n;
        // 디버거 쓸 줄 모르면 재귀가 끝까지 이해가 안감;;; + 왜 앞에서 스택을 했는가
        // 재귀를 위해
    }
}