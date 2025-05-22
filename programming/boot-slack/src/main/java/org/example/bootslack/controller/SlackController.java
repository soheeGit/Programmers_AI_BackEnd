package org.example.bootslack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.example.bootslack.dto.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/slack")
public class SlackController {
    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody SlackMessage message) throws Exception {
//        String url = "";

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(message);
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
//                .uri(URI.create(url))
                .uri(URI.create(webhookUrl))
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.noContent().build();
    }
}