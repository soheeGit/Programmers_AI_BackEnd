import java.util.Arrays;

public class Solution01 {
    public static void main(String[] args) {
        // 문자열
        // StringBuffer? -> TIL -> 알고리즘 시간 개선을 위해 쓰는 비법
        // concat, + 를 같은 걸 하면 -> 자꾸 새로운 객체가 생겨요
        // 메모리? -> 어 힘들게... 우울해질게... -> 막아야한다.
        // StringBuffer -> Buffer로 임시로 관리하다가 마지막에 toString함.
        // 마치? char[]처럼인데... 유동적임. 길이 유동적임.
        // 차? 체어? 카? 캐릭터? ...? varchar 바차! 차라고 일반적으로 부름.
        // 어쨌든 뭔가 반응속도를 최적화하고 싶은데 쿼리고 뭐고 DSL이고 다함
        // 이제 남은 건 Buffer뿐이야... -> 최후는 아니지만 자주 쓰는 수단
        String hello = "hello";
        System.out.println("hello = " + hello);
        String newHello = new String(hello); // new String("hello");
        System.out.println("newHello = " + newHello);
        System.out.println("(hello == newHello) = " + (hello == newHello));
        System.out.println("System.identityHashCode(hello) = " + System.identityHashCode(hello));
        System.out.println("System.identityHashCode(newHello) = " + System.identityHashCode(newHello));
        System.out.println("hello.equals(newHello) = " + hello.equals(newHello));
        // StringBuffer
        long startTime = System.currentTimeMillis();
//        String game = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 1_000_000; i++) {
//            game += (i + 1) * 3;
//            System.out.println("game = " + game);
            sb.append((i + 1) * 3);
        }
        String result = sb.toString();
//        System.out.println("result = " + result);
        System.out.println("수행시간 : " + (System.currentTimeMillis() - startTime));

        // StringBuilder
        startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
//            game += (i + 1) * 3;
//            System.out.println("game = " + game);
            builder.append((i + 1) * 3);
        }
        String result2 = builder.toString(); // 조금 더 17에 뭔가 최적화;;;
//        System.out.println("result2 = " + result2);
        System.out.println("수행시간 : " + (System.currentTimeMillis() - startTime));
        // JDK 5, JDK 9를 기준으로 한 String의 변천사는 누군가가 정리해줄 것

        String sample = "우리는 소프트웨어를 개발하고, 또 다른 사람의 개발을 "
                + "도와주면서 소프트웨어 개발의 더 나은 방법들을 찾아가고 "
                + "있다. 이 작업을 통해 우리는 다음을 가치 있게 여기게 되었다.";

        System.out.println("sample.length() = " + sample.length()); // 스페이스 포함
        System.out.println("sample.substring(10, 50) = " + sample.substring(1, 5));
        // 시작 인덱스 포함, 끝 인덱스 제외 (이런게 중요)

        String eng = "I see dead people";
        System.out.println("eng.toUpperCase() = " + eng.toUpperCase());
        System.out.println("eng.toLowerCase() = " + eng.toLowerCase());

        String eng2 = "They not like us, they not like us, they not like us";
        System.out.println("eng2.split(\", \") = " + eng2.split(", "));
        System.out.println("Arrays.toString(eng2.split(\", \")) = " + Arrays.toString(eng2.split(", ")));
        // https://regexr.com/, 정규표현식

        System.out.println("eng2.toLowerCase().replaceAll(\"they\", \"drake\") = " + eng2.toLowerCase().replaceAll("they", "drake"));
        System.out.println(eng2.toLowerCase().replaceAll("they", "drake").replaceAll("us", "kendrick lamar"));

        String triple = """
                {
                    "name" : "kendrick",
                    "grammy" : true
                }
                """; // 백틱이나 파이썬 3중 큰따옴표와 유사
        // jdk 15 이후, 다른 html, json, sql과 궁합이 좋다. format까지 쓰면 좋다.
        System.out.println(triple);
    }
}
