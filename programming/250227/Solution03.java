import java.util.Random;

public class Solution03 {
    public static void main(String[] args) {
        // 예외처리는 흐름제어의 일부로 보기도 함
        Solution03 sol = new Solution03();
        try {
            System.out.println("오늘 행운의 숫자는 " + sol.getLuckyNumber(25, 75));
        } catch (MaxLimitException e) {
            System.out.println("한도 초과!");
        } catch (MinLimitException e) {
            System.out.println("한도 미만!");
        } catch (Exception e) {
            System.out.println("알 수 없는 에러");
        }
    }

    int getLuckyNumber(int minLimit, int maxLimit) throws Exception {
        int luckyNumber = new Random().nextInt(100);
        // 0 (포함) ~ 100 (제외)
        if (luckyNumber > maxLimit) {
            // return이 없다! 자체는 흐름제어로 구현하고 싶은데...
            // 1 : 그냥 자체로 try-catch로 묶는다 -> X
            // 2 : 상위 호출 메서드에게 일임한다
//            throw new Exception("max limit 초과");
            throw new MaxLimitException();
        }
        if (luckyNumber < minLimit) {
//            throw new Exception("min limit 초과");
            throw new MinLimitException();
        }
        return luckyNumber;
    }
}

class MaxLimitException extends Exception {}
class MinLimitException extends Exception {}
