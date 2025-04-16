package org.example.bootapi.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.bootapi.model.entity.Diary;
import org.example.bootapi.model.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    public List<Diary> getAllDiaryList() throws Exception {
//        return diaryRepository.findAll();
        return diaryRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Diary createDiary(Diary diary) throws Exception {
        if (diary.getTitle().isEmpty()) {
            throw new BadRequestException("제목이 비었습니다!");
        }
        if (diary.getContent().isEmpty()) {
            throw new BadRequestException("내용이 비었습니다!");
        }
        return diaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(String id) throws Exception {
        if (!diaryRepository.existsById(id)) {
            throw new Exception("지울게 없네요...");
        }
        diaryRepository.deleteById(id);
    }

    @Override
    public Diary getDiaryById(String id) throws Exception {
        return diaryRepository.findById(id).orElseThrow();
    }
}