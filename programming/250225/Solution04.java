import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution04 {
    // static -> 클래스가 소유한 메서드라 객체를 만들지 않아도 호출 가능
    public static void main(String[] args) {
        Solution04 solution = new Solution04(); // 인스턴스 vs 객체
        // 클래스를 통해서 찍어낸 객체가 인스턴스다.
        // 클래스가 아닌 형태로 객체가 만들어질 수 있어요? -> JS 프로토타입 -> 객체.
        System.out.println(solution.getNumbers());
    }

    // 필드는 중립적이고, 속성(attribute) -> 엔터티(JPA, ORM?), 멤버변수
    // 필드, 속성, 멤버변수 -> 의존성 주입을 할 때 '필드'라고 부름
    // 멤버변수 <-> 매개변수
    // VO -> Value Object, POJO -> Plain Old Java Object
//    int numberOfNumbers = 45;
    int numberOfNumbers = 100;
    //    int numberOfDraws = 6;
    int numberOfDraws = 10;
    // 이거를 환경변수, 컨테이너, DB, 스토리지 이런걸로 뽑아서 이거 자체로 수정할 필요가 없게.

    // OOP -> 절차지향에서 왜 객체지향으로 가는가. 그리고 함수지향(함수형)에도 왜 결국 객체지향을 포기하지 못하는가
    // return - 매개변수가 없는. 기본적 return.
    List<Integer> getNumbers() {
        // 45개 번호. 6개를 추첨?
        List<Integer> listOfNumbers = new ArrayList<>();
//        for (int i = 1; i <= 45; i++) {
        for (int i = 1; i <= numberOfNumbers; i++) {
            listOfNumbers.add(i);
        }
        System.out.println("listOfNumbers = " + listOfNumbers);
        Collections.shuffle(listOfNumbers); // Collections -> List -> Shuffle
        // 원본에 영향을 줌
        System.out.println("listOfNumbers = " + listOfNumbers);
//        List<Integer> subList = listOfNumbers.subList(0, 6); // 끝점을 제외한 시작 인덱스부터 끝까지 추출.
        List<Integer> subList = listOfNumbers.subList(0, numberOfDraws); // 끝점을 제외한 시작 인덱스부터 끝까지 추출.
        Collections.sort(subList); // 정렬 -> 오름차순
        return subList;
    }
}
