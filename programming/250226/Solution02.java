public class Solution02 {
    public static void main(String[] args) {
        Lecture front = new FrontLecture();
        Lecture back = new BackLecture();
        front.study();
        back.study();
        front.awake();
        back.awake();
        // 이런 메서드들이 있다는 '보장'을 할 수 있음
        Lecture[] lectures = {front, back};
        // 사용하는 입장에서 Lecture 인터페이스를 implements -> 사용의 안정성
        for (Lecture lecture : lectures) {
            System.out.println(lecture);
            lecture.study();
            lecture.awake();
        }
    }
}

// 설계하는 입장에서 이렇게 해줄테니까 너희가 해!
interface Lecture {
    // 실제 메서드가 구현이 되었나?
    void study();
    void awake();
}

class FrontLecture implements Lecture {
    @Override
    public void study() {
        System.out.println("AI가 다 해줄 거임");
    }

    @Override
    public void awake() {
        System.out.println("shell 중 최고는 몽쉘");
    }
}

class BackLecture implements Lecture {

    @Override
    public void study() {
        System.out.println("속도와 비용이 여러분들 성과입니다");
    }

    @Override
    public void awake() {
        System.out.println("자바를 잡아드리겠습니다");
    }
}