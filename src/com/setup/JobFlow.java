package com.setup;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticmapreduce.model.HadoopJarStepConfig;
import com.amazonaws.services.elasticmapreduce.model.JobFlowInstancesConfig;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;

import java.util.ArrayList;
import java.util.Collection;

public class JobFlow {
    private AmazonElasticMapReduceClient amazonElasticMapReduceClient;

    public JobFlow(AWSCredentials awsCredentials) {
        amazonElasticMapReduceClient = new AmazonElasticMapReduceClient(awsCredentials);
    }

    public void create(String jobName){
        RunJobFlowRequest runJobFlowRequest = new RunJobFlowRequest();
        runJobFlowRequest.setName(jobName);
        runJobFlowRequest.setInstances(instances());
        runJobFlowRequest.setSteps(jarSteps());
        runJobFlowRequest.setLogUri("s3n://xconf");
        amazonElasticMapReduceClient.runJobFlow(runJobFlowRequest);

    }

    private JobFlowInstancesConfig instances() {
        JobFlowInstancesConfig instances = new JobFlowInstancesConfig();
        instances.setInstanceCount(1);
        instances.setMasterInstanceType("m1.small");
        instances.setSlaveInstanceType("m1.small");
        instances.setHadoopVersion("0.20");
        return instances;
    }

    private Collection<StepConfig> jarSteps() {
        Collection<StepConfig> jarSteps = new ArrayList<StepConfig>();
        StepConfig stepConfig = new StepConfig();
        stepConfig.setName("JarStep");
        stepConfig.setHadoopJarStep(hadoopJarStep());
        jarSteps.add(stepConfig);
        return jarSteps;
    }

    private HadoopJarStepConfig hadoopJarStep() {
        HadoopJarStepConfig hadoopJarStep = new HadoopJarStepConfig();
        Collection<String> args = new ArrayList<String>();
        args.add("s3n://xconf/input");
        args.add("s3n://xconf/output");
        hadoopJarStep.setArgs(args);
        hadoopJarStep.setJar("s3n://xconf/wordcount.jar");
        return hadoopJarStep;
    }

    public static void main(String[] args) {
        JobFlow jobFlow = new JobFlow(new Credentials());
        jobFlow.create("test1");
    }
}
