package org.example.bootthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootthymeleaf.model.dto.UpdateWordForm;
import org.example.bootthymeleaf.model.dto.WordForm;
import org.example.bootthymeleaf.model.entity.Word;
import org.example.bootthymeleaf.model.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//        model.addAttribute("words", wordRepository.findAll());
        // 방법 1. sort한다 (추천은 안함. 간단한 가설 검증)
//        model.addAttribute("words",
//                wordRepository.findAll().stream()
//                    .sorted((a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()))
        // 뒤집는 방법 1 : reverse하는 옵션이 어딘가에 있으니까 그걸 쓰세요(는 권장안함)
        // -> for문을 할 때 뒤부터 세는 for문을 하세요...
        // 뒤집는 방법 2 : 이런 식으로 패러미터를 바꿔 a, b -> b, a
//                    .sorted((b, a) -> a.getCreatedAt().compareTo(b.getCreatedAt()))
        // 뒤집는 방법 3 : -를 붙인다 (boolean이면 not(!)을 하고)
//                    .sorted((a, b) -> -a.getCreatedAt().compareTo(b.getCreatedAt()))
//                    .toList()); // 오름차순. -> 데이터가 등장하는 방향과 데이터가 커지는 방향이 같을 때
        // ascending <-> descending (내림차순)
        // (스트림화) 앞에와 뒤를 불러와서 생성일자를 비교해 그걸로 정렬해 그리고 그걸 다시 리스트로 만들어
        // -> 모델로 전달
        // model.addAttribute("message", message);

        // 방법2. 쿼리 같은 걸 만들어줘야하는데... 기준을 createdAt으로 잡아야겠네?
        model.addAttribute("words",
                wordRepository.findAllByOrderByCreatedAtDesc());
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

    @PostMapping("/update")
    public String updateWord(@ModelAttribute UpdateWordForm form, RedirectAttributes redirectAttributes) {
        // JPA는 업데이트용 메서드나 기능이 따로 없어요
        // JPA는 수정용이 따로 없어요
        // -> 교체 개념이에요 => put <-> patch : 멱등성 (TIL)
        // JPA : Jakarta Persistence API
        Word oldWord = wordRepository.findById(form.getUuid()).orElseThrow();
        oldWord.setText(form.getNewWord());
        wordRepository.save(oldWord);
        redirectAttributes.addFlashAttribute("message", "정상적으로 교제되었습니다. %s".formatted(oldWord.getUuid()));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteWord(@RequestParam("id") String uuid, RedirectAttributes redirectAttributes) {
        wordRepository.deleteById(uuid);
        redirectAttributes.addFlashAttribute("message", "정상적으로 삭제되었습니다. %s".formatted(uuid));
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