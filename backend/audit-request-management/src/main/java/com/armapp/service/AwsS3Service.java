package com.armapp.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Dibya Prakash Ojha
 * @date : 18-Jul-22
 * @project : audit-request-management
 */

@Service
@Slf4j
public class AwsS3Service {

    private static final Logger LOG = LoggerFactory.getLogger(AwsS3Service.class);

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${application.bucket.name}")
    private String s3BucketName;

    public String generatePreSignedUrl(String filePath,
                                       String bucketName,
                                       HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10); //validity of 10 minutes
        return amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }

    // for uploading a file to s3 bucket
    public String uploadFile(MultipartFile file, String filePrefix) {
        String fileName = null;
        try {
            File fileObj = convertMultiPartFileToFile(file);
            fileName = filePrefix + file.getOriginalFilename();
            amazonS3.putObject(new PutObjectRequest(s3BucketName, fileName, fileObj));
            Files.delete(fileObj.toPath());
//            fileObj.delete();
        } catch (AmazonServiceException e) {
            LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
        } catch (IOException ex) {
            LOG.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
        }
        return "File Uploaded: " + fileName;
    }

    // for downloading a file from s3 bucket
    @Async
    public S3ObjectInputStream downloadFile(String fileName) {
        LOG.info("Downloading file with name {}", fileName);
        return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
    }

    // for deleting a file from s3 bucket
    public String deleteFile(String fileName) {
        amazonS3.deleteObject(s3BucketName, fileName);
        return fileName + " removed ...";
    }

    // for getting all the files in a bucket
    public List<String> listFiles() {
        ObjectListing objectListing = amazonS3.listObjects(s3BucketName);
        List<String> files = new ArrayList<>();
        if (objectListing != null) {
            List<S3ObjectSummary> s3ObjectSummariesList = objectListing.getObjectSummaries();
            if (!s3ObjectSummariesList.isEmpty()) {
                for (S3ObjectSummary objectSummary : s3ObjectSummariesList) {
                    files.add(objectSummary.getKey());
                }
            }
        }
        return files;
    }

    // for converting multipart file to file format
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream outputStream = new FileOutputStream(convertedFile)) {
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            LOG.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
        }
        return convertedFile;
    }

    // @Async annotation ensures that the method is executed in a different thread
    // for downloading file from the s3 Bucket
    @Async
    public S3ObjectInputStream findByName(String fileName) {
        LOG.info("Downloading file with name {}", fileName);
        return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
    }

    // for uploading file to the s3 bucket
    @Async
    public void save(final MultipartFile multipartFile) {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            final String fileName = LocalDateTime.now() + "_" + file.getName();
            LOG.info("Uploading file with name {}", fileName);
            final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
            amazonS3.putObject(putObjectRequest);
            Files.delete(file.toPath()); // Remove the file locally created in the project folder
        } catch (AmazonServiceException e) {
            LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
        } catch (IOException ex) {
            LOG.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
        }
    }
}
