import java.util.Arrays;

public class Solution02 {
    public static void main(String[] args) {
        // 배열 복사
        String[] setList = {
                "squabble up",
                "HUMBLE.",
                "DNA.",
                "euphoria",
                "man at the garden",
                "peekaboo (feat. AzChike)",
                "luther",
                "All The Stars",
                "Not Like Us",
                "tv off (feat. Lefty Gunplay)"
        };
        System.out.println("setList = " + setList);
        // 주소값 나온다 -> 배열은 객체라서
        System.out.println("Arrays.toString(setList) = " + Arrays.toString(setList));
        // 배열은 변환을 통해서만 문자열로 표시할 수 있다.

        // SZA -> feat? -> 표시가 안되어 있어...
        String[] setList2 = setList; // 재할당
        setList2[6] += " (feat. SZA)";

        System.out.println("setList2 = " + Arrays.toString(setList2));
        System.out.println("setList = " + Arrays.toString(setList)); // 원본이 영향을 받았음;;;

        // 아 그래? 그러면 사본을 만들자.
        String[] setList3 = new String[setList.length]; // 기존의 setList 모양과 일치하는 배열
        for (int i = 0; i < setList3.length; i++) {
            setList3[i] = setList[i]; // 새롭게 할당을 함
        } // 복사 (얕은 복사가 될 위험성)
        setList3[7] += " (feat. SZA)";
        System.out.println("setList = " + Arrays.toString(setList));
        System.out.println("setList3 = " + Arrays.toString(setList3));
        System.out.println("System.identityHashCode(setList) = " + System.identityHashCode(setList));
        System.out.println("System.identityHashCode(setList2) = " + System.identityHashCode(setList2));
        System.out.println("System.identityHashCode(setList3) = " + System.identityHashCode(setList3));

        String[][] winners = {
                {"Not Like Us", "Kendrick Lamar"},
                {"Cowboy Carter", "Beyonce"},
                {"Espresso", "Sabrina Carpenter"},
                {"Die with a Smile", "Lady Gaga & Bruno Mars"}
        }; // 이중 배열
        System.out.println("winners = " + Arrays.toString(winners)); // 아 한번만 변환한다고??
        // 깊숙히 끝까지는 못해? deep한 걸 원하세요?
        System.out.println("winners = " + Arrays.deepToString(winners)); // deep하게 끝까지
        // 1층 때문에... 만약에 저 위 같이 복사를 하면...
        String[][] winners2 = new String[winners.length][winners[0].length];
        for (int i = 0; i < winners2.length; i++) {
            winners2[i] = winners[i];
        }
        winners2[3][1] = "Stefani Joanne Angelina Germanotta & Bruno Mars";
        System.out.println("winners = " + Arrays.deepToString(winners)); // 얕은 복사
        System.out.println("winners2 = " + Arrays.deepToString(winners2));
        // 참조값(주소값)을 담고 있을 때 그게 복사가 되어서 서로 연결관계가 끊기지 않았다 -> 얕은 복사 (swallow)
        // container 의존성 주입류? 할당과 얕은 복사 사이 어딘가... 싱글턴 패턴 류? -> 효율적 메모리 관리를 위해
        // 그러나 우리가 일반적으로는 이런 연결관계 원하지 않아.
        // 깊게(깊은) 복사는 없어? 주소값이 아니라 해당 값 자체에 대해 서로 연결 끊는 복사는 없어? deepCopy. (깊이! 깊이!)
        String[][] winners3 = new String[winners.length][winners[0].length];
        // 이렇게 하면 안되요 -> 얘도 실은 얕은 복사다 for문으로 한 번 찢어서 해줘야한다.
        // 그럼 다른 방법 없어요? -> 있다. stream이 당신을 구원해줄 것 (예고)
//        System.arraycopy(winners, 0, winners3, 0, winners.length); // 이것조차 얕은 복사다.
        for (int i = 0; i < winners3.length; i++) {
            // forEach 류와 궁합이 맞는 프로그래밍
//            System.arraycopy(winners[i], 0, winners3[i], 0, winners[i].length); // 해당 위치에 다른 사본을 주입
            // 위는 함수형 프로그래밍으로 처리 가능
            // map 류랑 조합.
            winners3[i] = Arrays.copyOfRange(winners[i], 0, winners[i].length); // 사본 만들어서 대입/할당
            // 안됨. '문'.
        }
        winners3[0][1] += " (With. DJ Mustard)";
        System.out.println("winners = " + Arrays.deepToString(winners));
        System.out.println("winners3 = " + Arrays.deepToString(winners3));
    }
}
