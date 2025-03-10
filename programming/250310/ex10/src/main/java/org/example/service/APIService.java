package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.model.APIParam;
import org.example.model.ModelResponse;
import org.example.model.ModelType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class APIService {
    private static final APIService instance = new APIService();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Dotenv dotenv = Dotenv.load();
    private final String groqToken;
    private final String togetherToken;
    private final String groqGuide;
    private final String togetherGuide;

    public static APIService getInstance() {
        return instance;
    }

    private APIService() {
        groqToken = dotenv.get("GROQ_KEY");
        togetherToken = dotenv.get("TOGETHER_KEY");
        groqGuide = dotenv.get("GROQ_GUIDE");
        togetherGuide = dotenv.get("TOGETHER_GUIDE");
    }

    public String callAPI(APIParam apiParam) throws Exception {
        String url;
        String token;
        String instruction;
        switch (apiParam.model().platform) {
            case GROQ -> {
                url = "https://api.groq.com/openai/v1/chat/completions";
                token = groqToken;
                instruction = groqGuide;
            }
            case TOGETHER -> {
                url = "https://api.together.xyz/v1/chat/completions";
                token = togetherToken;
                instruction = togetherGuide;
            }
            default -> throw new Exception("Unsupported platform");
        }

        String body = """
                {
                         "messages": [
                           {
                             "role": "system",
                             "content": "%s"
                           },
                           {
                             "role": "user",
                             "content": "%s"
                           }
                         ],
                         "model": "%s"
                       }
                """.formatted(instruction, apiParam.prompt(), apiParam.model().name);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Authorization", "Bearer %s".formatted(token))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        ModelResponse modelResponse = objectMapper.readValue(responseBody, ModelResponse.class);
        String content = modelResponse.choices().get(0).message().content();
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        return objectMapper.writeValueAsString(map);
    }
}