package com.github.zeros.core;

import java.util.List;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public interface ZConfBuilder<T> {

    /**
     * mapper 的作用：
     *  1. 类型转换
     *  2. 合法性校验 mapper 实现的原则：
     *          1. Fail Fast，仔细校验格式，如果不合法直接抛出异常，框架层会自动返回上一个正确的值或者默认值
     *          2. 切忌把 mapper 当作变更回调向外产生副作用，如果 mapper 抛出异常，很容易产生状态不一致 如下配置方式，如果 ZConf 平台上配置了非法的正则，mapper 会抛出 PatternSyntaxException ZConf
     * 就会使用上一次的合法值或者默认值
     * ```java
     *  private final ZConf  patternZConf = ZConfs.ofString("team.biz.pattern", "baidu\\.com")
     *               .mapper(Pattern::compile)
     *               .build();
     * ```
     */
    <T2> ZConfBuilder<T2> mapper(ConfigMapper<T, T2> mapper);

    ZConf<T> build();

}
