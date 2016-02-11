package com.shashank.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by shashank on 2/2/16.
 */
public class UnsafeUtil {

    public static Unsafe fetchInstance() throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }

}