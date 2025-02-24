import java.util.*;

public class Solution03 {
    public static void main(String[] args) {
        // Collection -> Stream
        // List<T> -> <> 가변적이라 제너릭. OOP
        List<String> list = new ArrayList<>(); // 원시타입은 뭐가 필요하다 wrapper 클래스가 필요
        // 제너릭의 특징 : 클래스가 들어가야함 -> 원시타입(int, long, double, boolean, ...) -> 클래스가 아니다.
        // -> 클래스가 아닌데 마치 클래스인냥 처리하기 위해 구현해놓은 감싸기 위한 (wrap) 전용 클래스가 존재
        // List 타입 -> 인터페이스, 구현체. -> 객체
        // List라는 표준적인 약속에 ArrayList 구체적인 클래스를 넣는다.
        // 나는 C언어의 구조체가 머리속에 떠오(를 수밖에 없...)랐다.
        // OOP이기도 하다... -> OOP 진도를 나가고 있습니다
        // 잘 모르겠다 -> 인터페이스<어떤 타입을 넣을지 클래스 표시> 변수명 = new 실제구현한타입(클래스)<>();
        // (가변적) 길이를 안정해도 되고, 정해도 되고 (대부분 안정함. 정할 거면?) 배열썼지.
        list.add("Java");
        list.add("Python");
        list.add("JS"); // 메서드로 작업 (<-> 배열과 다른점)
        System.out.println("list = " + list); // 객체 자체가 출력된다.
        // get, set, remove... 그냥 해보세요. 인덱스 -> get. set 설정
        // 반복문 순회도 된다. -> js array. 자세한 사용?
        System.out.println("list.size() = " + list.size()); // size()가 크기임.

        // Map과 Set 들어봤는데?
        // Map -> key, value (맵) / set -> 모두가 key이자 value (집합)
        // Map. 시간복잡도를 위해서 공간복잡도를 희생해서 시간을 높이는 방식 (해시)
        // Set. 중복을 제거해야할 경우. 두 개 이상의 집합 간 연산. JS에서 했었음
        Map<String, Integer> map = new HashMap<>();
        map.put("자바", 100);
        map.put("파이썬", 200);
        System.out.println("map = " + map);
        System.out.println("map.size() = " + map.size());

        Set<String> set = new HashSet<>();
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        set.add("자바");
        System.out.println("set = " + set);
        System.out.println("set.size() = " + set.size());
    }
}
