package com.github.zeros.util;

import static com.github.zeros.util.ByteConverUtil.byteToInt;
import static com.github.zeros.util.ByteConverUtil.byteToLong;
import static com.github.zeros.util.ByteConverUtil.byteToShort;
import static com.github.zeros.util.ByteConverUtil.intToByte;
import static com.github.zeros.util.ByteConverUtil.longToByte;
import static com.github.zeros.util.ByteConverUtil.shortToByte;

import java.io.File;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class ByteConverUtilTest {

    @Test
    public void shortToByteTest() {
        short num = 2;
        byte[] b = shortToByte(num);
        short res = byteToShort(b);
        System.out.println(res);
        Assert.assertEquals(res, num);
    }

    @Test
    public void intToByteTest() {
        int num = 4;
        byte[] b = intToByte(num);
        int res = byteToInt(b);
        Assert.assertEquals(num, res);
    }

    @Test
    public void longToByteTest() {
        long num = 8;
        byte[] b = longToByte(num);
        long res = byteToLong(b);
        Assert.assertEquals(num, res);
    }

    @Test
    public void testArrayCopy() {
        byte[] arr = {1, 2, 3};
        byte[] arr2 = {4, 5};
        byte[] res = new byte[8];
        System.arraycopy(arr, 0, res, 0, 3);
        System.arraycopy(arr2, 0, res, 3, 2);
        System.out.println(Arrays.toString(res));
        System.out.println(File.separator);
    }
}
