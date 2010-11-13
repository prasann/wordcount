package com.setup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class S3SetupTest {
    private S3Setup s3;
    private String bucketName;

    @Before
    public void setUp() {
        bucketName = "xconftest1";
        s3 = new S3Setup(new Credentials());
    }

    @Test
    public void shouldVerifyBucketCreation() {
        s3.createBucket(bucketName);
        assertTrue(s3.doesBucketExist(bucketName));
    }

    @Test
    public void shoudlAddContentsToBucket(){
        String tempKey = "input1";
        File tempFile =  null;
        s3.createBucket(bucketName);
        try {
            tempFile = File.createTempFile("sample", ".txt");
        } catch (IOException e) {
            fail();
        }
        s3.addObjectsToBucket(bucketName, tempKey, tempFile);
        assertNotNull(s3.getObject(bucketName,tempKey));
        s3.deleteObject(bucketName,tempKey);
    }

    @After
    public void tearDown() {
        s3.deleteBucket(bucketName);
    }

}
