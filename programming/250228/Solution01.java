import llm.*;
import slack.Slack;

public class Solution01 {
    public static void main(String[] args) {
        // LLM 슬랙봇으로 복습하자
        // 왜 복습인가? - 1. 어제 분명 수업한 내용이 있다 2. 우리는 LLM과 슬랙봇을 하였습니다
        LLM llm = new LLM();
        // method<String> ... C# -> TypeScript, Dart
        String prompt = System.getenv("GEMINI_PROMPT");
        String result = llm.callAPI(LLM.LLMModel.GEMINI_2_0_FLASH, """
                {
                  "contents": [
                    {
                      "role": "user",
                      "parts": [
                        {
                          "text": "%s"
                        }
                      ]
                    }
                  ],
                }
                """.formatted(prompt));
        // 여러분들의 과제 (시간을 들여서 해보세요)
        Slack slack = new Slack();
    }
}



