public class Solution02 {
    public static void main(String[] args) {
        // 변수, 상수
        // java 변수 -> 타입
        // int, long, double, String, boolean, 클래스...
        // -> 당하면 알게된다. (몇몇 케이스에서 요구되는 경우가 있어요.)
        // long은 알아야해. double도 알아야해. String[] Map<K, V>..
        int num = 1;
        System.out.println("num = " + num); // 변수이름 = 값(soutv)
        // 숫자 + 문자를 연결하면 자동으로 변환된다? 자바스크립트랑 비슷하네...
        // 비트연산자를 조금 다뤄보려고는 해요(?)
        // 변수의 자리를 만드는 걸 선언, 값을 넣는 걸 대입, 할당
        // 이걸 한 번에 한다 -> 초기화한다 => 뒤에 가서 싱글턴.
        // 여기는 const가 없어요? 당연히 있죠. 바꾸고 싶지 않은 값.
        double pi = 3.14;
        pi = 4; // 뭔가 우주항공에 문제가 생기면 이러한 값들의 계산 문제
        final  double pi1 = 3.14;
        // pi1 = 4;     java: cannot assign a value to final variable pi1
        // 중요한 점 : js와 차이 let, const라는 키워드를 쓰는 게 아니라
        // 변수를 선언하거나 초기화할때 각각 데이터의 형식을 의미하는 '타입'을 키워드로 쓴다
        // 그리고 타입은 한 번 정해지면 바꿀 수 없는데, 거기에 들어가는 값은 바꿀 수 있음
        // 그 값조차도 못바꾸게하려면 추가적으로 final이라는 키워드를 붙여주면 그건 '상수'라고 함

    }
}
