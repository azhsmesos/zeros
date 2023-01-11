package com.github.zeros.core.config;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.ProtocolMessageEnum;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public enum ConfigType implements ProtocolMessageEnum {

    NULL(0),
    INT32(1),
    INT64(2),
    DOUBLE(3),
    BOOLEAN(4),
    STRING(5),
    BYTES(6),
    LIST(7),
    MAP(8),
    ENCRYPT(9),
    GZIP(10),
    UNRECOGNIZED(-1);


    private final int value;


    private ConfigType(int value) {
        this.value = value;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public EnumValueDescriptor getValueDescriptor() {
        return null;
    }

    @Override
    public EnumDescriptor getDescriptorForType() {
        return null;
    }
}
