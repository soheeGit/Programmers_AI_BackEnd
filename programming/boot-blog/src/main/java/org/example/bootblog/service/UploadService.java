package org.example.bootblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
public class UploadService {
    // lombok value가 아님 주의! (application.yml에서 불러오겠다
    private final String bucketName;
    //    private final String region;
    private final String url;
    //    private final String accessKey;
//    private final String secretKey;
    private final S3Client s3Client;

    // S3 -> 외부 서비스 -> Supabase -> 끌어온건 이해함? -> 주입해서 Service.
    public UploadService(
            @Value("${aws.s3.bucketName}") String bucketName,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.url}") String url,
            @Value("${aws.s3.accessKey}") String accessKey,
            @Value("${aws.s3.secretKey}") String secretKey) {
        this.bucketName = bucketName;
//        this.region = region;
        this.url = url;
//        this.accessKey = accessKey;
//        this.secretKey = secretKey;
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(url))
                .credentialsProvider(() -> new software.amazon.awssdk.auth.credentials.AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return accessKey;
                    }

                    @Override
                    public String secretAccessKey() {
                        return secretKey;
                    }
                })
                .forcePathStyle(true)
                .build();
    }

    public String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = "%s_%s".formatted(UUID.randomUUID().toString(), file.getOriginalFilename()); // UUID 코드 + _ + 원래이름
                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build();
                s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
                return fileName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("뭔가 크게 잘 못 됌");
    }
}