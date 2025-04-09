package org.example.bootthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootthymeleaf.model.dto.WordForm;
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
//        Word word = new Word();
//        word.setText("고양이");
//        wordRepository.save(word);
        model.addAttribute("words", wordRepository.findAll());
//        model.addAttribute("message", message);
        // 타임리프에서 이미 폼을 이미 정의된 걸로 쓰려면 Model을 통해서 전달해야합니다
        model.addAttribute("wordForm", new WordForm()); // 주입함!
        return "index"; // forward
    }

    @PostMapping("/word")
    public String addWord(WordForm wordForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "끝말잇기 추가");
        Word word = new Word();
        word.setText(wordForm.getWord());
        wordRepository.save(word);
        return "redirect:/";
    }

    @PostMapping("/reset")
    public String resetWords(RedirectAttributes redirectAttributes) {
        wordRepository.deleteAll(); // 고양이들아 안녕!
        // Model을 쓸 수 없는 이유 -> forward
//        redirectAttributes.addAttribute("message", "전체 삭제되었습니다!");
        // 주소창을 통해서 전달했기에 Parameter로 해서 받아서 Model로 넣어줘야했다면...
//        redirectAttributes.addFlashAttribute("message2", "전부 DELETE 되었습니다!");
        redirectAttributes.addFlashAttribute("message", "끝말잇기 기록 리셋");
        // 바로 model로 넣어준다...
        // message -> Parameter
        // 주소 창에 노출이 된다
        return "redirect:/";
    }
}