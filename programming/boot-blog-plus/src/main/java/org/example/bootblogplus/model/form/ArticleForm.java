package org.example.bootblogplus.model.form;

import org.example.bootblogplus.model.entity.Article;
import org.springframework.web.multipart.MultipartFile;

public record ArticleForm(String title, String content, MultipartFile imageFile) {
    public Article formToEntity(String imageUrl) {
        Article article = new Article();
        article.setTitle(this.title);
        article.setContent(this.content);
        article.setImageUrl(imageUrl);
        return article;
    }
    public static ArticleForm empty() {
        return new ArticleForm("", "", null);
    }
}
