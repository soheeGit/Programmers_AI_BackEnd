import java.math.BigDecimal;
import java.util.Scanner;

/** java doc 2 */
public class Solution01 {
//    주석 : 똑같음
    /// 어 이건 뭐에요? java doc
//    main
    public static void main(String[] args) {
        // 다 주석이에요
        ///  다 주석이에요
        /* 우린 주석이야 */
        /** 근데 뭔가 다르지 */
        // 메서드, 클래스에 대한 설명을 java doc을 통해 작성 가능 (특수한 기능을 하는 주석)
        System.out.println("Hello World"); // 이거 ㄱ나니?
        // java doc을 왜 알려줄까? 잘난 척하려고? TIL?

        // 숫자 int (정수 -> 소수점 및 자리가 없다, 일반적(개별적 계산을 할 때),
        // 20억 또는 40억(부호가 있으면 앞뒤로 가져야 돼서...), 4바이트, 32비트)
        // integer
        int money = 0;
        System.out.println("money = " + money);
        Scanner scanner = new Scanner(System.in);
//        money = scanner.nextInt(); // 무언가를 입력 받아서 그걸 int로 변환해서 넣어주세요
        System.out.println("money = " + money);
        // integer로 품을 수 없는 것? 20억이 넘어가면? -> 금융권 아니면 유저가 만드는 데이터가 많아...
        // 긴게 필요하겠죠? -> 그때 쓰는 것? long~
        // 숫자 long (정수 -> 아무 생각하고 싶지 않다 (한도에 대한 걱정하고 싶지 않아.)), 합계.
        // 무언가 카운트하고 누적이 되는데, 얼마나 커질지 모르겠어 근데 엄청 커질 것 같아 -> long
        // String -> 문자열은 그 자체에 대해서 길이 제한없는 것과 다름없다. -> 진법과 종속되지 않음.
        long bigMoney = 0; // 똑같음
        System.out.println("bigMoney = " + bigMoney);
//        bigMoney = scanner.nextLong();
        System.out.println("bigMoney = " + bigMoney); // 10000000000 -> 어? 그거 못너. (int)
        // seq. 자동으로 1씩 증가하는 pk 같은데 쓰인다... 그런데 요새는 보안적 이슈 등이 있어서 long으로 pk를 쓰는 것이
        // 과연 '공개된' 어떠한 자원에 대해서 시퀀셜한 pk는 지양하자 라는 분위기가 있다 (인조키를 형태로 뭘 둘수 밖엔 없는데...)
        // uuid v4,v6, 알파벳기반 해싱을 할지언정... 공개된 숫자 누적되는 거는 하지 말자? -> 곧 다음달에 설명할 것.
        // 1번째 시퀀셜하다 누적된다 -> 현재까지 얼마나 데이터가 쌓였는지 최종값으로 유추가능
        // -> 어? 너희 이정도 매출? 이정도 게시물? 이정도 회원수 있어? -> 추론 가능
        // 내가 userInfo에 들어갔는데 userInfo/1로 접속이 된다(?) -> 나 첫번째 회원이야? 어? 이거 뭐야...?
        // 어떤 규모든 좀 뭔가 이상함.
        // 2번째 brute-force -> 일단 1(0)부터 다 두드려볼께~ => 취약.
        // 부동소수점 double (float?) 15자리
        double dollar = 0.0;
        System.out.println("dollar = " + dollar);
//        dollar = scanner.nextDouble();
        System.out.println("dollar = " + dollar); // 1.0
        double dollar1, dollar2; // 선언을 같은 타입이라면 콤마(,)로 묶어서 가능
        dollar1 = scanner.nextDouble(); // 10.000
        dollar2 = scanner.nextDouble(); // 3.000
        double dollarSum = dollar1 + dollar2;
        System.out.println("dollar1 = " + dollar1);
        System.out.println("dollar2 = " + dollar2);
        System.out.println("dollarSum = " + dollarSum);
        System.out.println("0.1 + 0.2 = " + dollarSum);
        System.out.println("0.1 + 0.2 == 0.3 is " + (dollarSum == 0.3));
        // BigDecimal
        BigDecimal exactDollar1 = scanner.nextBigDecimal();
        BigDecimal exactDollar2 = scanner.nextBigDecimal();
        BigDecimal dollarSum2 = exactDollar1.add(exactDollar2);
        System.out.println("0.1 + 0.2 = " + dollarSum2);
        System.out.println("0.1 + 0.2 == 0.3 is " + (dollarSum2.equals(new BigDecimal("0.3"))));

        // String
        String guy1 = "chill guy";
        String guy2 = new String(guy1);
        System.out.println("guy1 = " + guy1);
        System.out.println("System.identityHashCode(guy1) = " + System.identityHashCode(guy1));
        System.out.println("guy2 = " + guy2);
        System.out.println("System.identityHashCode(guy2) = " + System.identityHashCode(guy2));
        System.out.println("guy1 == guy2 = " + (guy1 == guy2));
        System.out.println("guy1.equals(guy2) = " + guy1.equals(guy2));

        // 배열
        int[] arr = new int[5]; // 객체 -> 생성 new. 타입. 갯수(길이)
        int[] arr2 = {1, 2, 3, 4, 5}; // 바로 값을 넣으면서 생성할 때
        int[][] matrix = {{1, 2}};
        int[][] matrix2 = new int[1][2]; // 다차원 배열
        // 객체인데... 내장메서드가 없음 -> js나 파이썬과 그런 느낌의 것이 없음. collection 변환 혹은 stream 처리
        // -> 거의 무조건 index를 통한 수정만 함. (추가와 삭제 개념이 아님) -> 빈 박스를 가지고 공간 만들었다 편집 X
        // 인덱싱하는 것 똑같고, length (프로퍼티). -1 안먹고(파이썬 유저 정말정말 주의!)

        // 향상된 for문

        // intellij 기준 -> fori, iter로 하시면 됨
        // for (let i; i < arr.length; i++) {} 아니라고!!!
        // for (const v of arr) {} 아니라고!!!!
        // -> 고차함수 시도조차 하지마세요. 지금 여기아님. stream으로 나옴.
    }
}
