package com.travel.domain.common;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Uploader {
    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.archiveBucket}")
    private String archiveBucket;

    @Value("${cloud.aws.s3.dayBucket}")
    private String dayBucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.archiveImagePath}")
    private String archiveDefaultPath;

    @Value("${cloud.aws.s3.dayImagePath}")
    private String dayDefaultPath;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile multipartFile, String type) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        return upload(uploadFile, type);
    }

    private String upload(File uploadFile, String type) {
        String fileName = UUID.randomUUID() + "_" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName, type);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName, String type) {
        String bucket;
        if (type.equals("archive")) {
            bucket = archiveBucket;
        } else {
            bucket = dayBucket;
        }
        System.out.println(bucket);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        log.info("converting file");
        File convertFile = new File(System.getProperty("java.io.tmpdir") +
                System.getProperty("file.separator") +
                file.getOriginalFilename());
        log.info("converted file " + convertFile);
        System.out.println(convertFile);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        } else {
            removeNewFile(convertFile);
        }
        return Optional.empty();
    }

    public void deleteS3(String imageUrl, String type) {
        String bucket;
        String defaultPath;

        if (type.equals("archive")) {
            bucket = archiveBucket;
            defaultPath = archiveDefaultPath;
        } else {
            bucket = dayBucket;
            defaultPath = dayDefaultPath;
        }

        try {
            amazonS3Client.deleteObject(bucket, imageUrl.replace(defaultPath, ""));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        log.info("file deleted");
    }
}
