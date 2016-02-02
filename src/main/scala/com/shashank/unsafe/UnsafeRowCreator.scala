package com.shashank.unsafe

import org.apache.spark.sql.catalyst.expressions.{UnsafeProjection, SpecificMutableRow, UnsafeRow}
import org.apache.spark.sql.types.{StringType, DataType, LongType, IntegerType}
import org.apache.spark.unsafe.Platform
import org.apache.spark.unsafe.array.ByteArrayMethods
//import org.scalatest.{FunSuite, Matchers}
/**
 * Created by shashank on 1/2/16.
 */
object UnsafeRowCreator {

  def main(args: Array[String]) {
    val fieldTypes: Array[DataType] = Array()
    val row = new SpecificMutableRow(fieldTypes)
    //row.setLong(0, 0)
    val converter = UnsafeProjection.create(fieldTypes)
    val unsafeRow = converter.apply(row)


    //new UnsafeRowConverter(fieldTypes)
    /*val sizeRequired: Int = converter.getSizeRequirement(row)
    sizeRequired should be (8 + (3 * 8))
    val buffer: Array[Long] = new Array[Long](sizeRequired / 8)
    val numBytesWritten = converter.writeRow(row, buffer, Platform.LONG_ARRAY_OFFSET)
    numBytesWritten should be (sizeRequired)*/

    println("Unsafe row string is "+unsafeRow)
    println(unsafeRow.toString)
    /*unsafeRow.getLong(0) should be (0)
    unsafeRow.getLong(1) should be (1)
    unsafeRow.getInt(2) should be (2)*/
  }

}
