package org.example.searchbookmark.service;

import org.example.searchbookmark.model.vo.KeywordSearch;

import java.util.List;

public interface SearchService {
    List<KeywordSearch> searchByKeyword(String keyword) throws Exception;
}