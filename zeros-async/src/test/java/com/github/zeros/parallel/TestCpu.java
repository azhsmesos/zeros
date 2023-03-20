package com.github.zeros.parallel;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-03-20
 */
public class TestCpu {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                60,
                1000 * 60,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory());

        for (int i=0; i<10000; i++) {
            executor.execute(TestCpu::sum);
        }
    }

    public static void sum() {
        int res = 0;
        for (int i = 0; i < 1000000; i++) {
            res += i;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + res);
    }
}
