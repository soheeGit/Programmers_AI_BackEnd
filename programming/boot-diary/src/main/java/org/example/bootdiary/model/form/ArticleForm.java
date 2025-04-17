package org.example.bootdiary.model.form;

import org.springframework.web.multipart.MultipartFile;

public record ArticleForm(String title, String content, MultipartFile file) {
    public static ArticleForm empty() {
        return new ArticleForm(null, null, null);
    }
}