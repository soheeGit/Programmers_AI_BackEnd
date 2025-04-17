package org.example.bootdiary.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file) throws Exception;

    byte[] download(String filename) throws Exception;
}