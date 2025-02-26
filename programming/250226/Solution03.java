public class Solution03 {
    public static void main(String[] args) {
        Morgan morgan = new Morgan();
        morgan.talkAboutLastSuperBall();
        morgan.talkAboutNetflixAnime();
        morgan.talkAboutThePreviousChampions();
    }
}

// POJO, VO, DTO 데이터를 관리하기 위한 클래스들이 아니라면...
// 이 구분이 그렇게 민감하지 않음
abstract class Fan {
    String name; // 멤버변수(상수가 아닌) 품을 수 있음

    void talkAboutYourFavorites(String favorites) {
        System.out.println("제 최애는 " + favorites + "입니다");
    }

    abstract void buyGoods(); // 인터페이스적 결정
}

class Morgan implements KoreanBaseball, AmericanWestHipHop, SubCulture {
    @Override
    public void talkAboutLastSuperBall() {
        System.out.println("They not like us~ They not like us~ They not like us~");
    }

    @Override
    public void talkAboutThePreviousChampions() {
        System.out.println("와! 양현종! 와! 키아!");
    }

    @Override
    public void talkAboutNetflixAnime() {
        System.out.println("사카모토데이즈는 2기 확정인데 이제 2기 확정 나오고 좋아하는 밴드 애니메이션 팬들이 있다? (케이온 아님)");
    }
}

interface KoreanBaseball {
    //    String hello; // Interface는 상수가 아닌 형태의 멤버변수를 가질 수 없음
    String hello = "하이";
    // Interface는 '정해주는 것'이기 때문에 final되거나 constant 되지 않은
    // 암묵적 final 적용됨

    void talkAboutThePreviousChampions();
}

interface AmericanWestHipHop {
    void talkAboutLastSuperBall();
}

interface SubCulture {
    void talkAboutNetflixAnime();
}