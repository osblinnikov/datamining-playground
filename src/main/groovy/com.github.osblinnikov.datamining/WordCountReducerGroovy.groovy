package com.github.osblinnikov.datamining

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import org.apache.hadoop.mapreduce.Reducer.Context

import java.io.IOException

public class WordCountReducerGroovy extends
    Reducer<Text, IntWritable, Text, IntWritable> {

  public void reduce(Text text, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
    int sum = 0
    for (IntWritable value : values) {
      sum += value.get()
    }
    context.write(text, new IntWritable(sum))
  }
}
