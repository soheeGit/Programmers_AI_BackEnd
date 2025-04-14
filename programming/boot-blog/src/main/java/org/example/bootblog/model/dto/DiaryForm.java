package org.example.bootblog.model.dto;

import org.springframework.web.multipart.MultipartFile;

public record DiaryForm(String title, String content, MultipartFile imageFile) {
}