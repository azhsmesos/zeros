package com.github.ticket.common;

import lombok.Data;
import lombok.Getter;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-27
 */
@Getter
public enum TrainType {

    CRH2(1220),
    CRH5(1244);
    final int count;

    TrainType(int count) {
        this.count = count;
    }
}
