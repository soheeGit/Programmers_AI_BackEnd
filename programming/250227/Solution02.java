import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Solution02 {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        File file = new File("number.txt");
        if (!file.exists()) { // 존재 여부를 체크해서
            file.createNewFile(); // 없으면 파일을 만들어주고
        }
        // 파일 존재 여부를 체크해야함!
//        Scanner sc = new Scanner(System.in);
//        Scanner sc = null;
//        try {
////            Scanner sc = new Scanner(file);
//            sc = new Scanner(file);
//            while (sc.hasNextInt()) { // 문서에 처리할 수 있는 정수 있는지
//                int n = sc.nextInt();
//                System.out.println(n);
//            }
//            System.out.println("끝");
////            int n = sc.nextInt();
////            System.out.println(n);
//            sc.close(); // 에러나면 close가 안되는데?
//            // 복잡한 프로그램이면 이걸로 인해 메모리가 터지기도 합니다
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 안 만들어지면요? finally를 했는데 close할게 없어 - 무의미한 코드가 돼 - Exception
//            try {
//                sc.close(); // 이거 돼요 안돼요? -> 안돼요. scope문제
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            // 이게 한동안 정석이었어
//        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextInt()) { // 문서에 처리할 수 있는 정수 있는지
                int n = sc.nextInt();
                System.out.println(n);
            }
            System.out.println("끝");
        } catch (Exception e) {
            e.printStackTrace();
        }   // finally에서 암시적으로 스캐너 닫아줌
    }
}
