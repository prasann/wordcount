package com.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class MapperTest {
    private org.apache.hadoop.mapreduce.Mapper mapper;
    private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    private List<Pair<Text, IntWritable>> intermediateKeys;
    private Text sampleText;

    @Test
    public void shouldTestMapper() {
        mapper = new Map();
        mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>(mapper);
        intermediateKeys = new ArrayList<Pair<Text, IntWritable>>();
        sampleText = new Text("This is a sample Text This is a sample Text");

        try {
            intermediateKeys = mapDriver.withInput(null, sampleText).run();
        } catch (IOException e) {
            fail();
        }
        assertEquals(10,intermediateKeys.size());
    }
}
