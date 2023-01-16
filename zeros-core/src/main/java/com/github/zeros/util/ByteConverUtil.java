package com.github.zeros.util;

import groovy.lang.DelegatesTo.Target;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class ByteConverUtil {

    public static byte[] shortToByte(short num) {
        byte[] shortArr = new byte[2];
        shortArr[0] = (byte) (num >> 8 & 0xFF);
        shortArr[1] = (byte) (num & 0xFF);
        return shortArr;
    }

    public static byte[] intToByte(int num) {
        byte[] intArr = new byte[4];
        intArr[0] = (byte) (num >> 24 & 0xFF);
        intArr[1] = (byte) (num >> 16 & 0xFF);
        intArr[2] = (byte) (num >> 8 & 0xFF);
        intArr[3] = (byte) (num & 0xFF);
        return intArr;
    }

    public static byte[] longToByte(long num) {
        byte[] longArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            int dx = (longArr.length - 1 - i) * 8;
            longArr[i] = (byte) ((num >>> dx) & 0xFF);
        }
        return longArr;
    }

    public static short byteToShort(byte[] b) {
        return (short) (((short) (b[0] & 0xFF) << 8) | ((short) (b[1] & 0xFF)));
    }

    // todo 考虑 byte数组不足4个字节长度的的情况
    public static int byteToInt(byte[] b) {
        return ((b[0] & 0xFF) << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }

    public static long byteToLong(byte[] b) {
        return ((long) (b[0] & 0xFF) << 56)
                + ((long) (b[1] & 0xFF) << 48)
                + ((long) (b[2] & 0xFF) << 40)
                + ((long) (b[3] & 0xFF) << 32)
                + ((long) (b[4] & 0xFF) << 24)
                + ((long) (b[5] & 0xFF) << 16)
                + ((long) (b[6] & 0xFF) << 8)
                + ((long) (b[7] & 0xFF));
    }

}
