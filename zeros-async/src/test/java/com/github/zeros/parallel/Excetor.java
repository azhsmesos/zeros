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
        // 缺点 只能返回Object范形
        Wrapper<String, String> wrapper = Wrappers.newBuilder(String.class, String.class)
//                .work(w1)
                .build();

        // 缺点：需要使用new关键字，无业务美感
        Wrapper<String, String> wrapper2 = new Builder<String, String>().build();

        boolean success = Asyncstarter.starter(3000, wrapper, wrapper2);
        Assert.assertTrue(success);
    }
}
