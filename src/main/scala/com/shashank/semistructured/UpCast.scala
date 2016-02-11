package com.shashank.semistructured

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 10/2/16.
 */

case class Person2(name: String, age: String)

object UpCast {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val path = "src/main/resources/people.json"
    val peopleDS = sqlContext.read.json(path).as[Person2]
    println("Number of people with age = 35 are"+peopleDS.filter(_.age.equals("35")).count())
    peopleDS.show()
  }

}
