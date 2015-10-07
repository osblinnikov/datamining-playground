package com.github.osblinnikov.datamining;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends
    Reducer<Text, IntWritable, Text, IntWritable> {

  public void reduce(Text text, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
    int sum = 0;
    for (IntWritable value : values) {
      sum += value.get();
    }
    context.write(text, new IntWritable(sum));
  }
}
