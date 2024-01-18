package com.example.movies.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {
    @Autowired
    private AmazonS3 s3Client;
    @Value("${aws.s3.bucket}")
    private String bucket;

    public String uploadPoster(MultipartFile multipartFile) throws IOException {
        File file = convertMultipartToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucket,fileName,file));
        file.delete();

        return s3Client.getUrl(bucket,fileName).toString();
    }

    public File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());

       try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
           fileOutputStream.write(multipartFile.getBytes());
       }
       return file;
    }

}
