package com.shashank.sql

import org.apache.spark.sql.Encoders
import org.apache.spark.sql.catalyst.encoders.{OuterScopes, ExpressionEncoder}
import org.apache.spark.sql.catalyst.expressions.{GenericInternalRowWithSchema, UnsafeProjection, GenericMutableRow, GenericInternalRow}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by shashank on 27/1/16.
 */
object FromCollections {

  def main(args: Array[String]) {
    val sc: SparkContext = new SparkContext("local","meetup",new SparkConf())
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    val ds = Seq(1, 2, 3).toDS()
    ds.map(_ + 1).foreach(println(_))


  }

}
