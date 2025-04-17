package org.example.bootdiary.service;

import org.example.bootdiary.exception.BadDataException;
import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.form.ArticleForm;

import java.util.List;

// 타입
public interface ArticleService {
    List<Article> findAll();

    void save(Article article) throws BadDataException;

    Article findById(String uuid);

    void delete(String uuid);
}