package com.setup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


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

    @After
    public void tearDown() {
        s3.deleteBucket(bucketName);
    }

}
