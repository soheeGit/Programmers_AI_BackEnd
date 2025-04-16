package org.example.bootapi.service;

import lombok.RequiredArgsConstructor;
import org.example.bootapi.model.repository.DiaryRepository;
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
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final DiaryRepository diaryRepository;
    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.access-key}")
    private String accessKey;
    @Value("${supabase.bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String extension = file.getContentType().split("/")[1];
        String boundary = "Boundary-%s".formatted(uuid);

        String filename = "%s.%s".formatted(uuid, extension);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/storage/v1/object/%s/%s".formatted(url, bucketName, filename)))
                .header("Authorization", "Bearer %s".formatted(accessKey))
                .header("Content-Type", "multipart/form-data; boundary=%s".formatted(boundary))
                .POST(ofMimeMultipartData(file, boundary))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) {
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
            throw new Exception("뭔가 크게 잘못 되었다!");
        }
        return response.body(); // 바이너리 데이터 반환
    }
}
