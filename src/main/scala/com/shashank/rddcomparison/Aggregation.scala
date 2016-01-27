package com.shashank.rddcomparison

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 27/1/16.
 */
object Aggregation {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val lines = sc.textFile("src/main/resources/words.txt")
    val words = lines
      .flatMap(_.split(" "))
      .filter(_ != "")
    val wordCount = words.groupBy(_.toLowerCase).map(w => (w._1,w._2.size))

    println("RDD contents")
    wordCount.foreach(println)

    val linesDS = sqlContext.read.text("src/main/resources/words.txt").as[String]
    val wordsDS = linesDS
      .flatMap(_.split(" "))
      .filter(_ != "")

    val wordCountDS = wordsDS.groupBy(_.toLowerCase).count()


    println("Dataset contents")
    wordCountDS.show()

  }

}
