public class Solution03 {
    public static void main(String[] args) {
        Solution03 sol = new Solution03();
        String[] cases = {
                "()()", "(())()", ")()(", "(()(", "()())", "())(()"
        };
        boolean[] answers = {
                true, true, false, false, false, false
        };
        for (int i = 0; i < cases.length; i++) {
            System.out.printf("""
                    case%d (%s) : %s
                    """, i, cases[i], answers[i] == sol.solution(cases[i]) ? "PASS" : "FAIL");
        }
    }

    boolean solution(String s) {
        // 1. 열린 괄호 없이 닫힌 괄호가 등장 (x)
        // 2. 나중에 보니까 열린 괄호가 닫힌 괄호보다 많아 (x)
        // 3. ......(???)
        // 근본적인 원리란 무엇인가?
        int openBracketCounting = 0;
        for (char c : s.toCharArray()) { // 문자열 -> char -> 묶음(배열)
            System.out.println("c = " + c);
//            if (c == '(') { // 작은따옴표? char.
            if (c == 40) { // 작은따옴표? char.
                openBracketCounting++; // 늘려주면 된다.
                System.out.println("openBracketCounting = " + openBracketCounting);
                continue; // 강사님은 else가 싫다고 하셨어 (forEach와 else에 코딩 스타일 강요하지 말아주세요?)
                // 이렇게 흐름제어를 할 수 있는 상황에서는 else, else if 코드는 '클-린'하지 않음.
                // return, break, continue로 여기까지만 하고 뒤는 맡길게 할 수 있으면...
            }
            // (, )
            // if (c== ')')
            // 여기서부터 무조건 닫는 괄호
            if (openBracketCounting == 0) {
                System.out.println("여는 괄호 없이 닫는 괄호 등장");
                return false;
            }
            // 닫는 괄호가 등장하면 카운트를 감소
            openBracketCounting--;
        }
        // 만약에 모든 괄호가 짝을 이루었다면?
        return openBracketCounting == 0;
    }
}