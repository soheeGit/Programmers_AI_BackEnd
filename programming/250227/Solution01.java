import java.util.Random;

public class Solution01 {
    public static void main(String[] args) {
//        System.out.println(number); // 문법상 안돼요! 자바는 호이스팅 없음
        // 체크 예외 -> 무조건 예외처리를 해줘야되는 에러
        try {
            int number = 10;
            // JS -> 나도 문제 있는 거 알아, 근데 그냥 무한으로 즐겨요.
//            System.out.println(number / 0); // 0으로 나눈다
            System.out.println(throwRandom());
            // 빌드 자체를 실패 시키는 에러, 빌드는 되고 작동하다가 '어랏?'
            // 그래 어쩔건데?
//        } catch {} // 이거 올바르지 않아요!
        } catch (Exception e) { // 모든 일반적인 에러, 익셉션들은 이걸 처리가 됩니다
            e.printStackTrace(); // 에러에 관련된 주르르르르르르... 디버깅.
        } finally {
            System.out.println("어쨌든 실행이 된다");
            // try, catch로 가든 실행이 된다
            // 더 절대적인 의미? -> return까지 덮어 씌워요
        }
        System.out.println("도달할 수 있나?");
    }

    static double throwRandom() { // 인스턴스를 만들지 않아도 클래스 단위에서 사용 가능
        Random random = new Random();
        double result = random.nextDouble(); // 0 포함 ~ 1 미포함 double
        try {
            if (result > 0.5) {
                int error = 10 / 0;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        } finally {
            if (result == -1) {
                result = 9999;
            } else {
                result *= 1_000_000;
            }
            return result;
        }
//        return result; // 여기 들어가는게 맞는데...
    }
}
