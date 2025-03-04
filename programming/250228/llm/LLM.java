package llm;

import web_client.WebClient;

import java.io.IOException;

public class LLM extends WebClient implements ILLM {

    @Override
    public String callAPI(LLMModel model, String body) {
        String path = "";
        HttpMethod method = HttpMethod.GET;
        String[] headers = new String[0];
        String apiKey = "";
        switch (model.platform) {
            case GOOGLE -> {
                path = "https://generativelanguage.googleapis.com/v1beta/models/%s:%s?key=%s".formatted(
                        model.name, model.action.name, model.platform.apiKey);
                method = HttpMethod.POST;
                headers = new String[]{"Content-Type", "application/json"};
                logger.info(path);
            }
            case GROQ -> {
            }
            case TOGETHER_AI -> {
            }
        }
        String result = null;
        logger.info(body);
        try {
            result = sendRequest(makeRequest(path, method, body, headers));
        } catch (Exception e) {
            logger.severe(e.toString());
            throw new RuntimeException(e);
        }
        return result;
    }
}

/**
 * 다양한 LLM (Large Language Model) 플랫폼 및 모델에 대한 인터페이스를 제공합니다.
 */
interface ILLM {

    /**
     * 지원되는 LLM 플랫폼을 정의합니다.
     */
    enum LLMPlatform {
        /** Google의 Gemini 플랫폼 */
        GOOGLE(System.getenv("GEMINI_API_KEY")),
        /** Groq 플랫폼 */
        GROQ(System.getenv("GROQ_API_KEY")),
        /** Together AI 플랫폼 */
        TOGETHER_AI(System.getenv("TOGETHER_API_KEY"));
        final String apiKey;
        LLMPlatform(String apiKey) {
            this.apiKey = apiKey;
        }
    }

    /**
     * 지원되는 LLM 모델 및 관련 정보를 정의합니다.
     */
    enum LLMModel {
        /** Google Gemini 2.0 Flash 모델 */
        GEMINI_2_0_FLASH(LLMPlatform.GOOGLE, "gemini-2.0-flash", LLMAction.GENERATE_CONTENT),
        /** Groq Mixtral 8x7b 32768 모델 */
        MIXTRAL_8x7b_32768(LLMPlatform.GROQ, "mixtral-8x7b-32768", null),
        /** Together AI Stable Diffusion XL Base 1.0 모델 */
        STABLE_DIFFUSION_XL_BASE_1_0(LLMPlatform.TOGETHER_AI, "stabilityai/stable-diffusion-xl-base-1.0", null);

        /** LLM 모델이 속한 플랫폼 */
        final public LLMPlatform platform;
        /** LLM 모델의 이름 */
        final public String name;
        /** LLM 모델에서 지원하는 기능 목록 */
        final public LLMAction action;

        /**
         * LLMModel 열거형 생성자
         *
         * @param platform  LLM 모델이 속한 플랫폼
         * @param name      LLM 모델의 이름
         * @param functions LLM 모델에서 지원하는 기능 목록 (선택 사항)
         */
        LLMModel(LLMPlatform platform, String name, LLMAction action) {
            this.platform = platform;
            this.name = name;
            this.action = action;
        }

        enum LLMAction {
            GENERATE_CONTENT("generateContent");

            final String name;

            LLMAction(String name) {
                this.name = name;
            }
        }

    }

    /**
     * 지정된 LLM 모델의 API를 호출하고 결과를 반환합니다.
     *
     * @param model 호출할 LLM 모델
     * @param <T>   API 호출 결과의 타입 (String, Map, List, String[] 등)
     * @return API 호출 결과
     */
    String callAPI(LLMModel model, String body);
}