public class Solution01 {
    public static void main(String[] args) {
        Teacher young = new Teacher();
        Student kim = new Student(); // 생성자도 상속이 된다...
        young.useAI();
        young.useAI("여러분 3.7 정말 좋아요!"); // 오버로딩
        System.out.println("young = " + young); // 객체 주소값
        System.out.println("young.language = " + young.language);
        kim.useAI(); // 상속에 따라서 불러온 함수인데, 다른 기능해버림 -> 오버라이딩 -> 상속
        kim.useAI("근데 나는 ChatGPT 쓸거다!"); // 오버로딩 -> 타입시스템, 패러미터(매개변수)
        System.out.println("kim = " + kim);
        System.out.println("kim.language = " + kim.language);
//        kim.useLanguage();
        kim.useLanguage("Java17");
    }
}

// Teacher 클래스는 상속을 안받은 걸까? - 이 친구도 상속을 받았나?
// Object -> 플라톤의 '선' 이데아 수준의 객체. 절대적인 존재. 우리는 Object로 나와서 Object로 간다...
//class Teacher extends Object {
class Teacher {
    private String secret = "생각보다 친절함"; // 상위 클래스가 private 접근 제어를 하면...
    // 하위 클래스는 불러올 수가 없음
    protected String protectedSecret = "말이 많음";

    String language = "Java";

    Teacher() {
        System.out.println("강사가 만들어졌다!");
    }

    void useAI() {
        System.out.println("AI 전사가 되십시오!");
    }

    void useAI(String message) {
        System.out.println(message);
    }
}

// extends
class Student extends Teacher {
    String language = "Java11";
    @Override
    void useAI() {
        super.useAI(); // this는 나. super 직전 상위 클래스.
        System.out.println("AI 마법사가 되겠다!");
        // 상속 받은 메서드 위에(over) 새로운 메서드를 탑재(riding)
        // <-> 이거 구분해야겠다. 오버로딩
        // 패러미터의 숫자가 구성이 다를 경우 '추가적으로 over해서 loading'

        // 변수 등에 대한 우선순위
        // 1. 지역변수(local variable) : {}에서 새롭게 선언/초기화된 변수
        // 2. 매개변수(parameter) : 메서드를 통해 외부로부터 주입 받은 변수 -> 인자(인수)값.
        // 3. 내가 소유한 멤버변수, 필드
        // 4. 상속 받은 멤버변수, 필드
    }

    void useLanguage(String language) {
//        String language = "Java21"; // 매개변수가 코드블록 상 선언의 우선권을 가져감
        System.out.println(language + "(이)가 세상에서 제일 좋아!");
        System.out.println(this.language + "(이)가 세상에서 제일 좋아!");
        System.out.println(super.language + "(이)가 세상에서 제일 좋아!");
        // 코드블록 상의 인라인 값 (지역, 패러미터) -> 내 클래스 멤버변수 -> 내 상위클래스 멤버 변수
        // 메서드도 똑같다. -> 클래스의 메서드 -> 상위 클래스 메서드 -> ...
    }

    void saySecret() {
//        System.out.println("secret = " + secret);
        System.out.println("protectedSecret = " + protectedSecret);
        // private, protected, (), public
        // private는 진짜 그 클래스 내부에서만 호출 가능 (only this)
        // protected는 그 클래스 내부나 super로 호출 가능
        // default는 같은 패키지(폴더)라면 가능
        // public는 어서와 모두 널 기다려 (모든게 가능)
    }

    @Override
    public String toString() {
        // 상속 -> 기본은 주소값
        return "나는 학생이다!";
    }
}