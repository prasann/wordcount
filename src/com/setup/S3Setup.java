package com.setup;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.io.File;
import java.util.List;

public class S3Setup {
    private AmazonS3Client amazonS3Client;

    public S3Setup(AWSCredentials awsCredentials) {
        amazonS3Client = new AmazonS3Client(awsCredentials);
    }

    public void createBucket(String bucketName) {
        if (!amazonS3Client.doesBucketExist(bucketName))
            amazonS3Client.createBucket(bucketName);
    }

    public boolean doesBucketExist(String bucketName) {
        return amazonS3Client.doesBucketExist(bucketName);
    }

    public void addObjectsToBucket(String bucketName, String key, File file) {
        amazonS3Client.putObject(bucketName, key, file);
    }

    public void deleteBucket(String bucketName) {
        if (!amazonS3Client.doesBucketExist(bucketName))
            amazonS3Client.deleteBucket(bucketName);

    }
}
    
