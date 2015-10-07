package com.github.osblinnikov.datamining

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper
import org.apache.hadoop.mapreduce.Mapper.Context

import java.io.IOException
import java.util.Arrays
import java.util.List
import java.util.StringTokenizer

public class WordCountMapperGroovy extends
    Mapper<Object, Text, Text, IntWritable> {

  List<String> commonWords = Arrays.asList("the", "a", "an", "and",
      "of", "to", "in", "am", "is", "are", "at", "not")

  private final IntWritable ONE = new IntWritable(1)
  private Text word = new Text()

  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString()
    StringTokenizer tokenizer = new StringTokenizer(line, " \t,;.?!-:@[](){}_*/")

    while (tokenizer.hasMoreTokens()) {
      String nextToken = tokenizer.nextToken()
      if (!commonWords.contains(nextToken.trim().toLowerCase())) {
        context.write(new Text(nextToken), new IntWritable(1))
      }
    }
  }
}
