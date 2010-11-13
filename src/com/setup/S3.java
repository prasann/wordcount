package com.setup;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;

public class S3 {
    private AmazonS3Client amazonS3Client;

    public S3(AWSCredentials awsCredentials) {
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

    public S3Object getObject(String bucketName,String key){
        return amazonS3Client.getObject(bucketName,key);
    }

    public void deleteObject(String bucketName,String key){
        amazonS3Client.deleteObject(bucketName,key);
    }

    public void deleteBucket(String bucketName) {
        if (amazonS3Client.doesBucketExist(bucketName))
            amazonS3Client.deleteBucket(bucketName);

    }
}
    
