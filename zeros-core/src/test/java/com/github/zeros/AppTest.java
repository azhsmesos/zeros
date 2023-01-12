package com.github.zeros;

import java.util.regex.Pattern;

import org.junit.Test;

import com.github.zeros.core.ZConf;
import com.github.zeros.core.ZConfs;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class AppTest {

    @Test
    public void hello() {
        ZConf<Pattern> conf = ZConfs.ofString("/tmp/tr", "www.baidu.com")
                .mapper(Pattern::compile)
                .build();
    }
}
