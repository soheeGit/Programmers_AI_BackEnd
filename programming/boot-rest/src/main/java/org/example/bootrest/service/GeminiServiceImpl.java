package org.example.bootrest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.example.bootrest.model.dto.AnimalRequestDTO;
import org.example.bootrest.model.dto.GeminiRequestDTO;
import org.example.bootrest.model.dto.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@Log
public class GeminiServiceImpl implements GeminiService {
    @Value("${gemini.api-key}")
    private String apiKey;

    @Override
    public String makeStory(AnimalRequestDTO dto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=%s".formatted(apiKey);
        String prompt = "%s를 이름으로 %s를 설명으로 하는 생명체에 대한 이야기를 작성해줘. 이야기만 평문 및 한글로 간결하게 200자 이내로 작성해줘.".formatted(dto.name(), dto.description());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(new GeminiRequestDTO(List.of(new GeminiRequestDTO.Content(List.of(new GeminiRequestDTO.Part(prompt))))))))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
        GeminiResponseDTO resp = objectMapper.readValue(response.body(), GeminiResponseDTO.class);
        return resp.candidates().get(0).content().parts().get(0).text();
    }
}