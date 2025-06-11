package org.example.bootsunflower.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootsunflower.dto.PromptForm;
import org.example.bootsunflower.entity.Prompt;
import org.example.bootsunflower.service.GeminiService;
import org.example.bootsunflower.service.PromptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PromptController {
    private final PromptService promptService;
    private final GeminiService geminiService;
    @Value("${server.baseurl}")
    private String baseUrl;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("promptForm", new PromptForm(""));
        return "index";
    }

    @PostMapping
    public String submit(PromptForm promptForm, Model model) {
        String result = geminiService.generate(promptForm.text());
        model.addAttribute("promptText", result);
        Prompt data = promptService.savePrompt(
                promptForm.text(),
                result
        );
        model.addAttribute("promptData", data);
        model.addAttribute("baseUrl", baseUrl);
        return "index";
    }

    @GetMapping("/history/{id}")
    public String history(@PathVariable("id") String id, Model model) {
        model.addAttribute("promptData", promptService.getPromptById(id));
        model.addAttribute("baseUrl", baseUrl);
        return "history";
    }
}