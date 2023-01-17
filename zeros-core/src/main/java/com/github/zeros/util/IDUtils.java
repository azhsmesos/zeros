package com.github.zeros.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-17
 *
 * 后续需要额外分出去做成一个分布式id组件
 */
public class IDUtils {

    private static final AtomicInteger index = new AtomicInteger();

    public static int newID() {
        return index.getAndIncrement();
    }
}
