package org.example.bootblog.service;

import lombok.RequiredArgsConstructor;
import org.example.bootblog.model.entity.Diary;
import org.example.bootblog.model.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    //생성자 의존성 주입 편하라고
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public List<Diary> getAllDiaryList() {
        return diaryRepository.findAll();
    }

    public Diary createDiary(Diary diary) {
        Diary save = diaryRepository.save(diary);
        return save;
    }
}
