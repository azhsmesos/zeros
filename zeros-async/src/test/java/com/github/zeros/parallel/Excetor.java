package com.github.zeros.parallel;

import org.junit.Assert;
import org.junit.Test;

import com.github.zeros.Asyncstarter;
import com.github.zeros.core.Wrapper;
import com.github.zeros.core.Wrapper.Builder;
import com.github.zeros.core.Wrappers;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */

public class Excetor {


    @Test
    public void testWrappers() throws Exception {

        Worker1 worker1 = new Worker1();
        Worker2 worker2 = new Worker2();
        Worker3 worker3 = new Worker3();

        Wrapper<String, String> wrapper = Wrappers.newBuilder(String.class, String.class)
                .id("1")
                .worker(worker1)
                .build();

        Wrapper<String, String> wrapper2 = Wrappers.newBuilder(String.class, String.class)
                .id("2")
                .worker(worker2)
                .build();

        Wrapper<String, String> wrapper3 = Wrappers.newBuilder(String.class, String.class)
                .id("3")
                .worker(worker3)
//                .next(wrapper2)
                .build();

        boolean success = Asyncstarter.starter(3000, wrapper, wrapper2, wrapper3);
        Assert.assertTrue(success);
    }
}
