package com.shashank.creation

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 27/1/16.
 */
case class Person2(name: String, age: Int)
case class Person3(name: String, age: String)

object SemistructuredDataHandling {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val path = "src/main/resources/people.json"
    //sqlContext.read.json(path).as[Person2].show()
    sqlContext.read.json(path).as[Person3].show()

  }

}
