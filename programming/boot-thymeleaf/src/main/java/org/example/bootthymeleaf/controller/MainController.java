package org.example.bootthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootthymeleaf.model.entity.Word;
import org.example.bootthymeleaf.model.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final WordRepository wordRepository;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("words", wordRepository.findAll());
        model.addAttribute("message", message);
        return "index";
    }

    @PostMapping("/reset")
    public String resetWords(RedirectAttributes redirectAttributes) {
        wordRepository.deleteAll();
        redirectAttributes.addAttribute("message", "전체 삭제되었습니다!");
        redirectAttributes.addFlashAttribute("message2", "전체 DELETE 되었습니다!");
        return "redirect:/";
    }
}
