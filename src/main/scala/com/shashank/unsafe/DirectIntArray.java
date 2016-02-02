package com.shashank.unsafe;

import com.sun.istack.NotNull;
import sun.misc.Unsafe;

/**
 * Created by shashank on 2/2/16.
 */
public class DirectIntArray {

    Unsafe unsafe;
    private final static long INT_SIZE_IN_BYTES = 4;
    private final long startIndex;

    public DirectIntArray(long size) throws Exception {
        unsafe = UnsafeUtil.fetchInstance();
        startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
        unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte) 0);
    }

    public void setValue(long index, int value) {
        unsafe.putInt(index(index), value);
    }

    public int getValue(long index) {
        return unsafe.getInt(index(index));
    }

    public long index(long offset) {
        return startIndex + offset * INT_SIZE_IN_BYTES;
    }

    public void destroy() {
        if(unsafe != null)
            unsafe.freeMemory(startIndex);
    }

    public static void main(String[] args) {
        DirectIntArray intArray = null;
        try {
            intArray = new DirectIntArray(5);
            intArray.setValue(0, 1);
            intArray.setValue(1, 2);
            System.out.println("First value of array is " + intArray.getValue(0));
            System.out.println("Second value of array is " + intArray.getValue(1));
            System.out.println("Third value of array is " + intArray.getValue(2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            intArray.destroy();
        }
    }
}
