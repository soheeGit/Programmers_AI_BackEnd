import java.util.ArrayList;
import java.util.List;

public class Solution06 {
    public static void main(String[] args) {
        // E(Element), T(Type), <K, V>
        List<String> list = new ArrayList<>();
        // <> 제너릭이 필요한데... 앞에 인터페이스/추상클래스/상위클래스에서 정의를 하면
        // 하위요소는 굳이 할 필요가 없음.
        list.add("힙합");
        System.out.println("list = " + list);

//        List<int> list2 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list2.add(1); // auto-boxing -> Integer
        for (int i : list2) {
            // Integer -> int -> auto unboxing
            System.out.println("i = " + i);
        }
        Box<Integer> box = new Box<>(1000);
        System.out.println("box = " + box.getData());
        System.out.println(box.sayType(1000));
        Box<String> box2 = new Box<>("있을때잘해");
        System.out.println("box2 = " + box2.getData());
    }
}

class Box<T> {
    private T data;

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        if (data instanceof String) { // true/false
            System.out.println("문자열이네요!");
        } else { // 예외처리가 비슷한 로직... // 혹은 상속관계로 인해서 무언가 분기가 필요할 때
            System.out.println("문자열이 아니네요!");
        }
        return data;
    }

    // PT 별도로 지정을 해서 클래스가 제네릭이 아니더라도 메서드 단위에서 제네릭을 쓸 수 있다
    // Dart/TS 문법이 잠깐 저를... 혼란스럽게 했는데... 머리속에 jam이 나서... 조금...
    public <PT> PT sayType(PT param) {
        // 클래스의 타입이 아니라 메서드에 타입을 줄 수 있다
        if (param instanceof String) {
            System.out.println("문자열이다");
        } else if (param instanceof Integer) {
            System.out.println("숫자다");
        }
        return param;
    }
}