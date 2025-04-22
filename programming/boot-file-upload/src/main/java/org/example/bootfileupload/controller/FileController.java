package org.example.bootfileupload.controller;

import lombok.extern.java.Log;
import org.example.bootfileupload.model.entity.MyFile;
import org.example.bootfileupload.model.repository.MyFileRepository;
import org.example.bootfileupload.service.StorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
@Log
public class FileController {
    private final StorageService storageService;
    private final MyFileRepository myFileRepository;

    public FileController(StorageService storageService, MyFileRepository myFileRepository) {
        this.storageService = storageService;
        this.myFileRepository = myFileRepository;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
            String dbFilename = storageService.upload(file);
            log.info(dbFilename);
            MyFile myFile = new MyFile();
            myFile.setFilename(dbFilename);
            myFileRepository.save(myFile);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MyFile>> getAll() {
        return ResponseEntity.ok(myFileRepository.findAll());
    }

    @GetMapping("/{bucketName}/{filename}")
    public ResponseEntity<byte[]> file(@PathVariable String bucketName, @PathVariable String filename) {
        try {
            byte[] fileBytes = storageService.download(bucketName, filename);

            // 페이지(템플릿)로 응답하지 않고, 데이터로 응답하겠다
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        myFileRepository.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }
}