package com.shashank.semistructured

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 10/2/16.
 */

case class Person3(name: String, age: Int)

object DownCast {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val path = "src/main/resources/people.json"
    val peopleDF = sqlContext.read.json(path)
    peopleDF.printSchema()
    val peopleDS = peopleDF.as[Person3]
    peopleDS.foreach(println(_))
  }

}
