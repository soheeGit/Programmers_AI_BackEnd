package org.example.bootblogplus.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootblogplus.model.form.ArticleForm;
import org.example.bootblogplus.service.BlogService;
import org.example.bootblogplus.service.StorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog") // Get? Post?
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final StorageService storageService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "블로그 글 목록");
        model.addAttribute("list", blogService.getAllArticles());
        return "/blog/list";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("title", "블로그 글 작성");
        model.addAttribute("form", ArticleForm.empty());
        return "/blog/form";
    }

    @PostMapping("/new")
    public String newArticle(ArticleForm form, RedirectAttributes redirectAttributes) {
//        Article article = new Article();
//        article.setTitle(form.title());
//        article.setContent(form.content());
        try {
            String imageUrl = storageService.upload(form.imageFile());
            blogService.createArticle(form.formToEntity(imageUrl));
            return "redirect:/blog";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/blog/new";
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
        byte[] imageData = storageService.download(fileName);
        ByteArrayResource resource = new ByteArrayResource(imageData);

        String contentType = getContentType(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    private String getContentType(String fileName) {
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        } else {
            return "application/octet-stream"; // 기본값
        }
    }
}