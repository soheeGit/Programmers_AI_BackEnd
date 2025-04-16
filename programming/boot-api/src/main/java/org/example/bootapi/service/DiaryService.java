package org.example.bootapi.service;

import org.example.bootapi.model.entity.Diary;

import java.util.List;

public interface DiaryService {
    List<Diary> getAllDiaryList() throws  Exception;

    Diary createDiary(Diary diary) throws Exception;

    void deleteDiary(String id) throws Exception;

    Diary getDiaryById(String id) throws Exception;
}