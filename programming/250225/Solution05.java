public class Solution05 {
    public static void main(String[] args) {
        System.out.println("Programmer.job = " + Programmer.job);
//        Programmer programmer = new Programmer(); // 새로운 객체를 new 키워드를 통해 만듭니다
        Programmer programmer = new Programmer("최인프라"); // 새로운 객체를 new 키워드를 통해 만듭니다
//        System.out.println("programmer.name = " + programmer.name);
        System.out.println("programmer.name = " + programmer.getName());
        // private으로 감춘 멤버 변수(필드)를 외부에서 접근할 수 있게 하는 함수
        programmer.setName("박백엔");
        System.out.println("programmer.name = " + programmer.getName());
//        programmer.call(); // private으로 막아서 접근 불가
        Programmer programmer2 = new Programmer("이", "풀스택");
        System.out.println("programmer2.name = " + programmer2.getName());
        // 문제가 무엇이냐? -> 그럼 애초에 우리는 생성자를 만든 적이 없는데? 그 땐 어떻게 한거야?
        // 그땐 굳이 지정을 안하면 '패러미터가 없는' 빈 생성자가 기본으로 주어집니다.
        // new Programmer() -> 멤버변수와 메서드만 가지고 객체를 인스턴스화해서 생성
        // new Programmer(programmerName)이 생기면서 직전 빈 생성자가 기본으로 주어지지 않음
        // 수동으로 만들어야함.
        programmer.setName("나는 파이썬 프로그래머다");
        programmer2.setName("나도 파이썬 프로그래머다");
        System.out.println("programmer.getName() = " + programmer.getName());
        System.out.println("programmer2.getName() = " + programmer2.getName());
        Programmer.job = "Coder";
        System.out.println("Programmer.job = " + Programmer.job);
        System.out.println("programmer.getName() = " + programmer.getName());
        System.out.println("programmer2.getName() = " + programmer2.getName());
    }
}

class Programmer {

    static String job = "Programmer";

    //    Programmer(String programmerName) {
    Programmer(String name) {
//        name = name; // 멤버변수 name을 찾아서
        this.name = name;
        // 매개변수 > 멤버변수 > 클래스변수 순으로 찾아요. 찾으면 해당 값으로 표시
        // 꼭 멤버변수로 찾고 싶으면?
        System.out.println("Programmer.job = " + Programmer.job);
    }

    Programmer(String familyName, String myName) {
        name = familyName + myName;
    }

    public String getName() {
//        return name;
        // 얼마나 호출되었는지 카운트를 한다던가...
        // 특정 자원(외부의 리소스, 연결...)이 요청될 때 메서드를 통해서 처리되도록...
//        return "나의 이름은 " + name; // 출력을 제한하거나 처리하고 싶어...
        return "나의 이름은 " + this.name + " " + job; // 출력을 제한하거나 처리하고 싶어...
    }

    public void setName(String name) {
        this.name = name;
    }

    // 접근제어자로 지정해줄 수 있음
    // public, protected, default, private
    // 변수명 블록 > 생성 > getter 및 setter
    private String name = "김개발";
    // public하고 private만 대부분 씀
    // 나는 롬복! -> 나는 어노테이션으로 모든 걸 할 수 있지...하다가...
    // 너 왜 롬복 써? 대답 못하면 바로... 사수에게 특강 들을 수 있다.
    private void call() {
        System.out.println("엉엉");
    }
}
