package com.github.zeros.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-14
 */
public class SupplierLazyTest {

    @Test
    public void testLazy() {
        SupplierLazy<List<Integer>> res = SupplierLazy.lazy(ArrayList::new);
        res.get().add(1);
        Assert.assertEquals(res.get().size(), 1);
    }
}
