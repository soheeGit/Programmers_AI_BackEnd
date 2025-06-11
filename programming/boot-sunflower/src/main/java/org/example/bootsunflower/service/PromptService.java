package org.example.bootsunflower.service;

import lombok.RequiredArgsConstructor;
import org.example.bootsunflower.entity.Prompt;
import org.example.bootsunflower.repository.PromptRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptService {
    final private PromptRepository promptRepository;

    public Prompt getPromptById(String id) {
        return promptRepository.findById(id).orElseThrow();
    }

    public Prompt savePrompt(String question, String answer) {
        Prompt prompt = new Prompt();
        prompt.setQuestion(question);
        prompt.setAnswer(answer);
        prompt.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        return promptRepository.save(prompt);
    }
}