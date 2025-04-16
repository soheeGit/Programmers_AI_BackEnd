package org.example.bootblogplus.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.bootblogplus.model.entity.Article;
import org.example.bootblogplus.model.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void createArticle(Article article) throws Exception {
        if (article.getTitle().isEmpty()) {
            throw new BadRequestException("제목 없음");
        }
        if (article.getContent().isEmpty()) {
            throw new BadRequestException("내용 없음");
        }
        articleRepository.save(article);
    }
}