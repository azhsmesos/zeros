package com.github.zeros.storage.config;

import static com.github.zeros.storage.config.Entry.ENTRY_HEADER_SIZE;
import static com.github.zeros.util.ByteConverUtil.byteToInt;
import static com.github.zeros.util.ByteConverUtil.byteToShort;
import static com.github.zeros.util.ByteConverUtil.intToByte;
import static com.github.zeros.util.ByteConverUtil.shortToByte;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class Serialization {

    public static byte[] encode(Entry entry) {
        byte[] buf = new byte[entry.getSize()];
        System.arraycopy(intToByte(entry.getKeySize()), 0, buf, 0, 4);
        System.arraycopy(intToByte(entry.getValueSize()), 0, buf, 4, 4);
        System.arraycopy(intToByte(entry.getPosition()), 0, buf, 8, 4);
        System.arraycopy(shortToByte(entry.getMark()), 0, buf, 12, 2);
        System.arraycopy(entry.getKey().getBytes(StandardCharsets.UTF_8), 0, buf, ENTRY_HEADER_SIZE,
                entry.getKeySize());
        return buf;
    }

    public static Entry decode(byte[] b) {
        Entry entry = new Entry();
        int keySize = byteToInt(subByte(b, 0, 4));
        int valueSize = byteToInt(subByte(b, 4, 4));
        int position = byteToInt(subByte(b, 8, 4));
        short mark = byteToShort(subByte(b, 12, 2));
        String key = new String(subByte(b, ENTRY_HEADER_SIZE, b.length - ENTRY_HEADER_SIZE), StandardCharsets.UTF_8);
        entry.setKeySize(keySize);
        entry.setValueSize(valueSize);
        entry.setPosition(position);
        entry.setMark(mark);
        entry.setKey(key);
        return entry;
    }

    private static byte[] subByte(byte[] b, int off, int length) {
        byte[] b1 = new byte[length];
        System.arraycopy(b, off, b1, 0, length);
        return b1;
    }
}
