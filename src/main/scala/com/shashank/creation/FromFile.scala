package com.shashank.creation

import org.apache.spark.{SparkConf, SparkContext}


/**
 * Created by shashank on 27/1/16.
 */
case class Person(name: String, age: Long)

object FromFile {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._


    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name.
    val path = "src/main/resources/people.json"
    val peopleDS = sqlContext.read.json(path).as[Person]
    peopleDS.filter(_.age > 20).show()
  }

}
