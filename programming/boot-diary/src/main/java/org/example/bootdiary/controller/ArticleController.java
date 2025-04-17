package org.example.bootdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.bootdiary.exception.BadDataException;
import org.example.bootdiary.exception.BadFileException;
import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.form.ArticleForm;
import org.example.bootdiary.service.AIService;
import org.example.bootdiary.service.ArticleService;
import org.example.bootdiary.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/article")
@Log // Logback
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final FileService fileService;
    private final AIService aiService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "글 목록 ✏️");
        model.addAttribute("list", articleService.findAll());
        return "article/list";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("title", "글 작성 🍎️");
        model.addAttribute("form", ArticleForm.empty());
        model.addAttribute("edit", false);
        return "article/form";
    }

    @PostMapping("/new")
    public String newArticle(ArticleForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            String filename = fileService.upload(form.file()); // 여기서 아예 에러가 터지게 하자!
            // 파일이 없다 -> 빈게 나옴. / 파일이 비었다 혹은 잘못된 파일이다 -> 예외처리 -> BadFileException
            Article article = new Article();
            article.setTitle(form.title());
            article.setContent(form.content());
            article.setFilename(filename);
            articleService.save(article);
            String result = aiService.answer(form.title() + "에 대해서 무슨 감정인지 한글로 분석해줘. 마크다운 쓰지 않은 평문으로. 간결하게.");
//            log.info(result);
            redirectAttributes.addFlashAttribute("message", result);
        } catch (BadFileException e) {
//            model.addAttribute("message", "잘못된 파일");
            model.addAttribute("message", e.getMessage());
            // 폼의 제목이랑 내용은 그대로 가져가고... 파일만 비워서 다시 폼으로 보냄
            model.addAttribute("form", new ArticleForm(form.title(), form.content(), null));
//            return "redirect:/article/new";
            return "article/form";
        } catch (BadDataException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("form", form); // 그대로 보내도 되겠네...
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e); // 500(?)
        }
        return "redirect:/article";
    }

    @GetMapping("/edit/{uuid}")
    public String editArticle(Model model, @PathVariable String uuid) {
        model.addAttribute("title", "글 수정 🍎️");
        Article prevArticle = articleService.findById(uuid);
        model.addAttribute("form", new ArticleForm(prevArticle.getTitle(), prevArticle.getContent(), null));
        model.addAttribute("edit", true);
        return "article/form";
    }

    @PostMapping("/edit/{uuid}")
    public String editArticle(ArticleForm form, Model model, @PathVariable String uuid) {
        try {
            String filename = fileService.upload(form.file()); // 여기서 아예 에러가 터지게 하자!
            // 파일이 없다 -> 빈게 나옴. / 파일이 비었다 혹은 잘못된 파일이다 -> 예외처리 -> BadFileException
            Article article = articleService.findById(uuid);
            article.setTitle(form.title());
            article.setContent(form.content());
            if (!filename.isEmpty()) { // 수정이니까... 기존 파일을 보호하는 셈
                article.setFilename(filename);
            }
            articleService.save(article);
        } catch (BadFileException e) {
//            model.addAttribute("message", "잘못된 파일");
            model.addAttribute("message", e.getMessage());
            // 폼의 제목이랑 내용은 그대로 가져가고... 파일만 비워서 다시 폼으로 보냄
            model.addAttribute("form", new ArticleForm(form.title(), form.content(), null));
//            return "redirect:/article/new";
            return "article/form";
        } catch (BadDataException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("form", form); // 그대로 보내도 되겠네...
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e); // 500(?)
        }
        return "redirect:/article";
    }

    @PostMapping("/delete/{uuid}")
    public String deleteArticle(@PathVariable String uuid, RedirectAttributes redirectAttributes) {
        articleService.delete(uuid);
        redirectAttributes.addFlashAttribute("message", "삭제 성공! %s".formatted(uuid));
        return "redirect:/article";
    }
}