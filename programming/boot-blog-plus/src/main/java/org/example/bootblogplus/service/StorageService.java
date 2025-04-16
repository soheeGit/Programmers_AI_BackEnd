package org.example.bootblogplus.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

@Service
public class StorageService {
    // lombok value가 아님 주의! (application.yml에서 불러오겠다
    private final String bucketName;
    private final S3Client s3Client;
    // S3 -> 외부 서비스 -> Supabase -> 끌어온건 이해함? -> 주입해서 Service.
    public StorageService(
            // Value -> application.yml => 끌어오겠다 => 코드 내부로.
            @Value("${aws.s3.bucketName}") String bucketName,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.url}") String url,
            @Value("${aws.s3.accessKey}") String accessKey,
            @Value("${aws.s3.secretKey}") String secretKey) {
        this.bucketName = bucketName;
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

    public String upload(MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            // 파일 이름 UUID_원래파일
//            String fileName = "%s_%s".formatted(UUID.randomUUID().toString(), file.getOriginalFilename()); // UUID 코드 + _ + 원래이름
            // 한글로 인해서 S3에 못들어가는 문제 해결 하는 코드
            // TIL : URL 인코딩으로 한글을 뭉게버리는 방법
//            String uuid = UUID.randomUUID().toString();
//            String extension = "";
//            String originalFilename = file.getOriginalFilename();
            // Null Safety 코드 반영
//            int dotIndex = Objects.requireNonNull(originalFilename).lastIndexOf(".");
//            if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
//                extension = originalFilename.substring(dotIndex + 1);
//            }
//            String fileName = "%s.%s".formatted(uuid, extension); // UUID 코드 + _ + 확장자
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
//                    .key(fileName)
                    .key(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
            return file.getOriginalFilename();
        }
        throw new BadRequestException("파일 누락");
    }

    public byte[] download(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }
}