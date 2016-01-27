package com.shashank.rddcomparison

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 27/1/16.
 */
object Operations {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val lines = sc.textFile("src/main/resources/people.csv")
    val words = lines
      .flatMap(_.split(","))
      .filter(_ != "")
    println("RDD contents")
    words.foreach(println)

    val linesDS = sqlContext.read.text("src/main/resources/people.csv").as[String]
    val wordsDS = linesDS
      .flatMap(_.split(","))
      .filter(_ != "")

    println("Dataset contents")
    wordsDS.show()
  }

}
