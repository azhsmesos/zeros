package com.github.zeros.storage.config;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class Entry {

    public static final int ENTRY_HEADER_SIZE = 14;

    private String key;

    private int keySize;

    private int valueSize;

    private short mark;

    private int position;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setValueSize(int valueSize) {
        this.valueSize = valueSize;
    }

    public short getMark() {
        return mark;
    }

    public void setMark(short mark) {
        this.mark = mark;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSize() {
        return ENTRY_HEADER_SIZE + this.keySize;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key='" + key + '\'' +
                ", keySize=" + keySize +
                ", valueSize=" + valueSize +
                ", mark=" + mark +
                ", position=" + position +
                '}';
    }
}
