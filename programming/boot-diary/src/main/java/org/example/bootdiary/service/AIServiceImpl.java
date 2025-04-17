package org.example.bootdiary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.example.bootdiary.model.dto.TogetherAPIDTO;
import org.example.bootdiary.model.dto.TogetherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@Log
public class AIServiceImpl implements AIService {
    @Value("${together.api-key}")
    private String apiKey;
    @Value("${together.model}")
    private String model;

    @Override
    public String answer(String prompt) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
//        String model = "meta-llama/Llama-3.3-70B-Instruct-Turbo-Free";
        String body = mapper.writeValueAsString(new TogetherAPIDTO(model, List.of(new TogetherAPIDTO.Message("user", prompt))));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.together.xyz/v1/chat/completions"))
                .header("Authorization", "Bearer %s".formatted(apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
//            return response.body(); // 후처리
            return mapper.readValue(response.body(), TogetherResponseDTO.class).choices().get(0).message().content();
        }
        throw new Exception("잘못된 결과");
    }
}