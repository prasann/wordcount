package com.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReducerTest {
    private org.apache.hadoop.mapreduce.Reducer reducer;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    private List<Pair<Text, IntWritable>> intermediateKeys;

    @Test
    public void shouldTestReducer(){
        reducer = new Reduce();
        reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
        reduceDriver.withInputKey(new Text("sample"));
        reduceDriver.withInputValue(new IntWritable(1));
        reduceDriver.withInputValue(new IntWritable(1));
        reduceDriver.withInputValue(new IntWritable(1));
        try {
            intermediateKeys = reduceDriver.run();
        } catch (IOException e) {
            fail();
        }
        assertEquals(new Text("sample"), intermediateKeys.get(0).getFirst());
        assertEquals(new IntWritable(3), intermediateKeys.get(0).getSecond());
    }
}                                               
