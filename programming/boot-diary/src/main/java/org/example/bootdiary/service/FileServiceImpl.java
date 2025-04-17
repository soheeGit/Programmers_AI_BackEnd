package org.example.bootdiary.service;

import lombok.extern.java.Log;
import org.apache.coyote.BadRequestException;
import org.example.bootdiary.exception.BadFileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@Service
@Log
public class FileServiceImpl implements FileService {
    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.access-key}")
    private String accessKey;
    @Value("${supabase.bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) throws Exception {
//        if (file.getOriginalFilename().isEmpty() && file.isEmpty()) {
        if (file.getOriginalFilename().isEmpty() && file.getSize() == 0) {
            // 이름도 없고, 내용도 없고.
            return ""; // 파일이 없다
        }
        if (file.getSize() == 0) {
            throw new BadFileException("내용이 없는 파일");
        }
        String contentType = file.getContentType();
        boolean isImage = contentType.startsWith("image");
        if (!isImage) {
            throw new BadFileException("첨부한 파일이 이미지가 아닙니다!");
        }
        String uuid = UUID.randomUUID().toString();
        // 기존의 걸 잘 사용...
        String extension = contentType.split("/")[1];
        String boundary = "Boundary-%s".formatted(uuid);
        String filename = "%s.%s".formatted(uuid, extension);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/storage/v1/object/%s/%s"
                        .formatted(url, bucketName, filename)))
                .header("Authorization", "Bearer %s".formatted(accessKey))
                .header("Content-Type", "multipart/form-data; boundary=%s".formatted(boundary))
                .POST(ofMimeMultipartData(file, boundary))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception(response.body());
        }
        return filename;
    }

    private HttpRequest.BodyPublisher ofMimeMultipartData(MultipartFile file, String boundary) throws IOException {
        List<byte[]> byteArrays = List.of(
                ("--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"\r\n" +
                        "Content-Type: " + file.getContentType() + "\r\n\r\n").getBytes(),
                file.getBytes(),
                ("\r\n--" + boundary + "--\r\n").getBytes()
        );
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    public byte[] download(String filename) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/storage/v1/object/%s/%s".formatted(url, bucketName, filename)))
                .header("Authorization", "Bearer %s".formatted(accessKey))
                .GET()
                .build();

        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new Exception("찾을 수 없는 파일");
        }
        return response.body();
    }
}