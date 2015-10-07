package com.github.osblinnikov.datamining

import com.twitter.scalding._

class WordCountJob(args: Args) extends Job(args) {
  TypedPipe.from(TextLine(args("input")))
    .flatMap { line => tokenize(line) }
    .groupBy { word => word } // use each word for a key
    .size // in each group, get the size
    .write(TypedTsv[(String, Long)](args("output")))

  // Split a piece of text into individual words.
  def tokenize(text: String): Array[String] = {
    // Lowercase each word and remove punctuation.
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }
}
