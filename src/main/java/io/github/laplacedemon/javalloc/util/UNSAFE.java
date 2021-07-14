package io.github.laplacedemon.javalloc.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UNSAFE {
    private final static Unsafe unsafe;

    static {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe u = null;
        try {
            u = (Unsafe) unsafeField.get(null);
        } catch (IllegalAccessException e) {
        }
        unsafe = u;
    }

    private UNSAFE() {
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }

    public static int getInt(byte[] data, long offset) {
        return unsafe.getInt(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setInt(byte[] data, long offset, int value) {
        unsafe.putInt(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }

    public static boolean getBoolean(byte[] data, long offset) {
        return unsafe.getBoolean(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setBoolean(byte[] data, long offset, boolean value) {
        unsafe.putBoolean(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }

    public static byte getByte(byte[] data, long offset) {
        return unsafe.getByte(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setByte(byte[] data, long offset, byte value) {
        unsafe.putByte(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }

    public static long getLong(byte[] data, long offset) {
        return unsafe.getLong(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setLong(byte[] data, long offset, long value) {
        unsafe.putLong(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }

    public static char getChar(byte[] data, long offset) {
        return unsafe.getChar(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setChar(byte[] data, long offset, char value) {
        unsafe.putChar(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }

    public static double getDouble(byte[] data, long offset) {
        return unsafe.getDouble(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset);
    }

    public static void setDouble(byte[] data, long offset, double value) {
        unsafe.putDouble(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + offset, value);
    }
}
