package com.shashank.unsaferow

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import org.apache.spark.sql.catalyst.expressions.{SpecificMutableRow, UnsafeProjection}
import org.apache.spark.sql.types._

/**
 * Created by shashank on 1/2/16.
 */
object UnsafeRowCreator {

  def main(args: Array[String]) {
    val fieldTypes: Array[DataType] = Array(NullType, DoubleType)
    val row = new SpecificMutableRow(fieldTypes)
    row.setNullAt(0)
    row.setDouble(1, 2.0)

    val bos = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(bos)
    oos.writeObject(row)
    println("Normal row byte array length " + bos.toByteArray.length)

    val converter = UnsafeProjection.create(fieldTypes)
    val unsafeRow = converter.apply(row)


    println("Unsafe row byte array length " + unsafeRow.getBytes.length)
  }

}
