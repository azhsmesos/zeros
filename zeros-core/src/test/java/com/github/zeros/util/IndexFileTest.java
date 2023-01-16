package com.github.zeros.util;

import static com.github.zeros.storage.config.Serialization.decode;
import static com.github.zeros.storage.config.Serialization.encode;

import java.io.IOException;

import org.junit.Test;

import com.github.zeros.storage.AppendFile;
import com.github.zeros.storage.config.Entry;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class IndexFileTest {

    @Test
    public void testEncode() {
        Entry entry = new Entry();
        entry.setKey("key");
        entry.setKeySize("key".getBytes().length);
        entry.setValueSize(10);
        entry.setPosition(100);
        entry.setMark((short) 0);
        byte[] data = encode(entry);
        Entry res = decode(data);
        System.out.println(res);
        System.out.println(res.getKey());
    }

    @Test
    public void appendTest() throws IOException {
        AppendFile file = new AppendFile("/tmp/zeros-data", "first");
        for (int i = 0; i < 10; i++) {
            Entry entry = new Entry();
            entry.setKey("key");
            entry.setKeySize("key".getBytes().length);
            entry.setValueSize(10);
            entry.setPosition(100);
            entry.setMark((short) 0);
            file.append(entry);
        }
    }
}
