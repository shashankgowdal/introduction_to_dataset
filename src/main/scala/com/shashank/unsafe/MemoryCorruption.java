package com.shashank.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by shashank on 2/2/16.
 */
class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED;
    }

    @Override
    public String toString() {
        return "Guard{" +
                "ACCESS_ALLOWED=" + ACCESS_ALLOWED +
                '}';
    }
}

public class MemoryCorruption {
    public static void main(String[] args) throws Exception {
        Guard guard = new Guard();
        Guard guard1 = new Guard();
        System.out.println("Access available "+guard.giveAccess());

        Unsafe unsafe = UnsafeUtil.fetchInstance();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);

        System.out.println("Access available " + guard.giveAccess());

        System.out.println("Before memory corruption - "+guard1);
        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(f), 121);
        System.out.println("After memory corruption - "+guard1);
    }
}
