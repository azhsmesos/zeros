package com.github.zeros.core.config;

import static com.google.common.base.Preconditions.checkArgument;

import com.github.zeros.core.parse.Parser;
import com.google.protobuf.StringValue;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public final class ConfigParsers {

    public static final Parser<Integer> INT_PARSER = value -> {
        checkType(ConfigType.INT32, value.getType());
        return UInt32Value.parseFrom(value.getData()).getValue();
    };

    public static final Parser<String> STRING_PARSER = value -> {
        checkType(ConfigType.STRING, value.getType());
        return StringValue.parseFrom(value.getData()).getValue();
    };

    public static final Parser<Long> LONG_PARSER = value -> {
        checkType(ConfigType.INT64, value.getType());
        return UInt64Value.parseFrom(value.getData()).getValue();
    };

    public static void checkType(ConfigType expected, ConfigType actual) {
        checkArgument(expected == actual,
                String.format("config type not match, expected: %s, actual: %s", expected, actual));
    }
}
