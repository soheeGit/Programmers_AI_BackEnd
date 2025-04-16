package org.example.bootapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(MultipartFile file) throws Exception;
    byte[] download(String filename) throws Exception;
}
