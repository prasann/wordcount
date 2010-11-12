package com.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(),new WordCount(),args);
    }

    public int run(String[] args) throws Exception {
        if(args ==null || args.length<2){
            System.out.println("Need to specify input and output path.");
            System.out.println("Usage: java -jar wordcount.jar <input-path> <output-path>");
            return 0;
        }
        System.out.println("Input from : " +args[0]);
        System.out.println("Output To: " +args[1]);
        Configuration configuration = getConf();
        Job job = new Job(configuration,"WordCount");
        job.setJarByClass(WordCount.class);
        
        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        return job.waitForCompletion(true)?0:1;
    }
}
