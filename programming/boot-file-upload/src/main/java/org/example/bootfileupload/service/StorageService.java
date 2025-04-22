package org.example.bootfileupload.service;

import lombok.extern.java.Log;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Log
public class StorageService {
    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.access-key}")
    private String accessKey;
    @Value("${supabase.pdf-bucket-name}")
    private String pdfBucketName;
    @Value("${supabase.img-bucket-name}")
    private String imgBucketName;
    @Value("${supabase.sheet-bucket-name}")
    private String sheetBucketName;

    public String upload(MultipartFile file) throws Exception {
        String contentType = Objects.requireNonNull(file.getContentType());
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String extension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);
        String boundary = "Boundary-%s".formatted(uuid);
        String filename = "%s.%s".formatted(uuid, extension);
        String bucketName;
        if (extension.equals("pdf")) {
            bucketName = pdfBucketName;
        } else if (extension.equals("xls") || extension.equals("xlsx")) {
            bucketName = sheetBucketName;
        } else if (contentType.contains("image")) {
            bucketName = imgBucketName;
        } else {
            // 다른 파일은?
            throw new BadRequestException("허용되지 않는 파일");
        }
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
//        return filename;
        return "%s/%s".formatted(bucketName, filename);
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

    public byte[] download(String bucketName, String filename) throws Exception{
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