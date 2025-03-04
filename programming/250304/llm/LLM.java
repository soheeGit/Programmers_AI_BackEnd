package llm;

import util.logger.MyLogger;
import util.logger.MyLoggerLevel;
import util.secret.MySecret;
import util.secret.NoEnvException;
import util.webclient.WebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LLM {
    private final MyLogger logger;
    private final WebClient webClient;
    private final MySecret secret;
    public LLM() throws NoEnvException {
        logger = MyLogger.getLogger();
        logger.setLevel(MyLoggerLevel.DEBUG);
//        logger.setLevel(MyLoggerLevel.INFO);
        secret = MySecret.getSecret();
        webClient = WebClient.getWebClient();
    }

    public String sendPrompt(ModelCategory model, String prompt) throws NoEnvException, IOException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        // URL과 BODY만 분기하면 된다
        map.put("method", "POST");
        map.put("headers", "Authorization;Bearer %s;Content-Type;application/json".formatted(secret.getSecret("TOGETHER_API_KEY")));
        switch (model) {
            case R1:
            case LLAMA:
                map.put("url", "https://api.together.xyz/v1/chat/completions");
                map.put("body", """
                    {
                    "model": "%s",
                    "messages": [{
                        "role": "user",
                        "content": "%s"
                    }],
                    "max_tokens": %d
                    }
                """.formatted(model.name, prompt, 2048));
                break;
            case FLUX:
                map.put("url", "https://api.together.xyz/v1/images/generations");
                map.put("body", """
                    {
                    "model": "%s",
                    "prompt": "%s"
                    }
                """.formatted(model.name, prompt));
                break;
            default:
                throw new RuntimeException(); // 대체 뭘 넣은거냐;;;
        }
        String result = webClient.sendRequest(webClient.makeRequest(map));
        logger.debug(result);
        switch (model) {
            case R1:
            case LLAMA:
                String content = result.split("content")[1].split("tool_calls")[0].substring(4);
                content = content.substring(0, content.length() - 1).strip();
                content = content.substring(0, content.length() - 2); // ",이라 2개를 제거해야 한다
                if (model.equals(ModelCategory.R1)) {
                    logger.debug(content);
                    content = content.split("/think")[1].substring(1).strip();
                }
                return content.replace("\\n", "");
            case FLUX:
                String url = result.split("url")[1].split("timings")[0].substring(4);
                url = url.substring(0, url.length() - 1).strip();
                url = url.substring(0, url.length() - 2);
                return url;
            default:
                throw new RuntimeException(); // 대체 뭘 넣은거냐;;;
        }
    }
}