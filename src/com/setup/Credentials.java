package com.setup;

import com.amazonaws.auth.AWSCredentials;

public class Credentials implements AWSCredentials{
    private String accessKeyId = "AKIAI26U4YU4TKDLUVTA";
    private String secretKey = "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";

    public String getAWSAccessKeyId() {
        return accessKeyId;
    }

    public String getAWSSecretKey() {
        return secretKey;
    }
}
