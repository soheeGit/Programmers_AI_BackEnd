package org.example.bootsecurity.controller;

import org.example.bootsecurity.model.domain.Memo;
import org.example.bootsecurity.model.domain.MemoForm;
import org.example.bootsecurity.model.mapper.MemoMapper;
import org.example.bootsecurity.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    private final MemoService memoService;

    public MainController(MemoMapper memoMapper, MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("memoList", memoService.findAll());
        model.addAttribute("memoForm", new MemoForm());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("memoForm", new MemoForm());
        return "add";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Memo memo = memoService.findById(id);
        model.addAttribute("memo", memo);
        return "update";
    }

    @PostMapping("/add")
    public String save(MemoForm form) throws Exception {
//        Memo memo = new Memo(0L, form.getText(), "");
        Memo memo = Memo.fromText(form.getText());
        memoService.create(memo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "%d를 정상적으로 삭제하였습니다.".formatted(id));
        memoService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam String text, RedirectAttributes redirectAttributes) {
        Memo oldMemo = memoService.findById(id);
        Memo newMemo = new Memo(oldMemo.id(), text, oldMemo.createdAt());
        memoService.update(newMemo);
        redirectAttributes.addFlashAttribute("msg", "정상적으로 수정되었습니다!");
        return "redirect:/";
    }

    @PostMapping("/delete-all")
    public String deleteAll(RedirectAttributes redirectAttributes) throws Exception {
        memoService.deleteAll();
        redirectAttributes.addFlashAttribute("msg", "전체삭제");
        return "redirect:/";
    }
}