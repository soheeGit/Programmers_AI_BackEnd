import java.util.Arrays;

public class Solution03 {
    public static void main(String[] args) {
        // 해보면 알아서
        // 굳이 하나하나 짚기보단
        // 헷갈린 만한거 3개 정도만 확실해보고,
        // 나머지는 JS에 대한 지식만으로도 Java 코딩이 어느정도 가능하구나
        // 프로그래밍 전반 (웹프로그래밍) + AI 도움 + 선임 프로그래머의 가이드
        // 잘못 계산한 것 아님. 딱 맞음.

        // 1번 String 비교
        String text1 = "안녕하세요"; // 객체다. ==으로 하면 주소값을 비교한다
        System.out.println("text1 = " + text1);
        String text2 = "안녕하세요"; // -> 재할당에 가까움.
        System.out.println("text2 = " + text2);
        boolean isEqual = text1 == text2;
        System.out.println("isEqual = " + isEqual);
        // ctrl + d, cmd + d => 많은 이들을 절망을 밀고 간 키배치???
        String text3 = new String("안녕하세요"); // 기존에 없던 String의 객체를 강제로 메모리에 넣음
        // String text3 = new StringBuffer().append("안녕하세요").toString(); // 기존에 없던 String의 객체를 강제로 메모리에 넣음
        System.out.println("text3 = " + text3);
        boolean isEqual2 = text1 == text3; // 이게 발견된다? 이게 발견되고, 이걸 놓쳤다?
        System.out.println("isEqual2 = " + isEqual2);
        System.out.println(System.identityHashCode(text1)); // 이 String이 결과적으로 어디를 가리키냐
        System.out.println(System.identityHashCode(text2));
        System.out.println(System.identityHashCode(text3)); // 이걸 가지고 ==을 처리
        System.out.println(text1.equals(text3)); // 이게 정석.
        // ==으로 그냥 개발함 -> 뭣도 모르고 일단은 통과됨 왠지 로그인이 되서 -> 실제 프로덕션될 때 ==이라 죄다 로그인 실패

        // int long 문제도 있는데... 이건 하다보면 깨달아요... 아;;; 왠만하면 long쓰자... (반복문 빼고는...)

        // 어? 파이썬에서 왔는데요... 자바스크립트에서 왔는데요... -> 무조건 한 번 당함.
        // 왜 C는 안당해요? 어 우리는 String이 없어. char[]야. -> 어 그래? 얘가 걔야? 그러면 이게 오히려 당연한데?
        // (이게 맞잖아? 아냐? 오히려 포인터 없어? 너무 좋아)

        // 2번 배열을 어떻게 만들지? 배열에 값을 어떻게 '추가'하지? -> 뭔가 많이 잘못됨
        // 파이썬은 차라리 나음 이들은 '리스트'로 배워서 배열이라고 하면 낯설음. 다른 존재라는 걸 직감함.
        // 근데 자바스크립트. 어? 배열? 나 얘 알아. 너 나 알지? (착각) -> 모든 파멸의 시작. => (연속극 시작)
        // 1편 일단 선언부터 못함.
        // 너무 자연스럽게 넘어갔지만 우리 자바에 세미콜론 꼭 붙여한다 이런 것도 다 생략함;;;
        // int[] numbers = []; // 진짜 천재인 것임. 그러나 한계. 당신이 파이썬과 자바스크립트란 유약한 언어로 시작한 죄.
        // 배열
        // 1) 배열은 객체입니다!!! (생성자로 원래 만들어야함 혹은 그에 준하는 메서드 등이 필요)
        // 2) 배열은 동일한 타입들의 나열입니다. (O) - 와 이걸 어떻게 알았냐... -> 타입[] 변수명, 타입 변수명[]으로 표시할 수 있음
        // 3) 배열은 크기가 정해져 있으며 변할 수 없습니다. - 크기(길이)를 알려줘야함
        // -> 처음부터 값을 초기화해버리며 고정해버리거나, 빈 배열을 만들며 그 길이(혹은 다차원일 경우 모양(shape)을 지정)
        int[] numbers = new int[7]; // 7개의 동일한 정수(소수점 없는) int 4바이트. 부호 여부 signed. (+-) unsigned (+)
        // 2^32 -10^9 ~ 10^9 20억. +-20억. => 40억. 뭔가 에러가 나서 오버플로우가 4x억에서 났다고 게임사가 실토. (???)
        // int overflow. back server... (???)
        // long 8바이트 -> 2^64. -10^19 ~ 10^19 -> 이거가 넘어가면 uuid 영역 -> 해싱영역 0.9해 -> 1.8해. 900경.
        // 게임에서는 가끔남 3d에서 좌표와 객체를 다루는 경우에서는 가끔 오버플로우가 남. 크래프트류 게임.
        // -> 이런데 이것도 빅데이터 영역에서는... 빈번하게 생성 삭제가 일어나다보면... 뭔가 기록했다는 게... 못버티기도 함.
        // 그러면 어떻게 해야해요? 어서와 대용량 처리의 세계에 (어서와~ 모두 널 기다려...) 해싱~ 분산~ 레디스~ 큐~ 트랜잭션~
        // 성능 자체나 속도 자체의 문제도 있는데, 만약에 long을 위협할 만한 '해' 단위의 무언가들의 비교와 연산처리? 여러분들은?
        // collision -> conflict 뭔가 둘이 어쩌다보니 다르다, collision은... 어... 재앙입니다. (손을 쓸 수 없어.)
        // 왜 어쩌다보니 동일한 애가 2이상이 존재하기 시작했네(????????????????) 뭐지?????????????????? 어?????????
        int[][] arr = {{1, 2}}; // 이중 배열 // {} 중괄호로 초기화가 안되는 건 아님. []로는 초기화 안되고 얘는

        System.out.println("arr = " + arr);     // 주소값 - 배열은 객체입니다!
        // 객체는 어딘가에 담겨있고, 그 담긴 주소가 기본적으로 출력시 기본값.
        // 문자열은 하도 너무 사람들이 출력하니까 (그 값 자체를) 그냥 그래 넌, 그 텍스트 묶음으 연결한 걸 출력해 라고 정의해버린것
        // toString -> 오버라이딩. 모든 객체는 주소가 찍혀 나온다
        // 어 그래요? 그러면 노란색인 걸 보니 답은? 눌러보면 나오지 않나요?
        // deep 하게 가자
        System.out.println("Arrays.deepToString(arr) = " + Arrays.deepToString(arr));

        int arr2[][] = {{1, 2}}; // 이런 역할이에요 아시겠어요? -> 어디서 나온 영향?

        System.out.println("arr2 = " + arr2);   // 주소값

        // BigInteger는 차라리 그런 숫자는 숫자가 아니라 일반적으론 코드로 치는게 맞아요. 그래서 String으로 처리함.
        // 일반적으로 거의 어플리케이션 단에서 계산이 어려운 건 DB단에서 많이 쳐줘요. long을 넘어가는 연산을 웬만하면 안줌.

        // 3번 향상된 for문
        String[] fruits = {"apple", "pear", "grapes"};
        for(int i = 0; i < fruits.length; i++){
            System.out.println(fruits[i]);
        } //iter
        for(String fruit: fruits) { //for(const v of arr) {}
            System.out.println(fruit);  // 향상된 for문은 나중에 배울 collection...
        }
    }
}
