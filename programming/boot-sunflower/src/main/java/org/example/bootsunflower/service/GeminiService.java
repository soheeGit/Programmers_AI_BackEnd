package org.example.bootsunflower.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${gemini.key}")
    private String geminiKey;

    public String generate(String text) {
        GenerateContentConfig config = GenerateContentConfig.builder()
                .systemInstruction(
                        Content.fromParts(
                                Part.fromText("입력하는 것에 대해서 네가 5~7년차 대리직급의 직장인이라고 생각하고 해당 입장에서 500자 미만의 잔소리를 해줘. 본인이 누군지는 굳이 언급하진 마. don't use any rich text or markdown ever.")
                        )
                ).build();
        try (Client client = Client.builder().apiKey(geminiKey).build()) {
            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.0-flash",
                            text,
                            config);
            return response.text();
        }
    }
}