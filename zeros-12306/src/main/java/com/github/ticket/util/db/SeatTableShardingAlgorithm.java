package com.github.ticket.util.db;

import java.util.Collection;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
public class SeatTableShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    private final static String PREFIX = "train_seat_";

    private String determineDB(int val) {
        int table = val % 10;
        if (table == 0) {
            table = 10;
        }
        return PREFIX + table;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        String actualTableName = determineDB(preciseShardingValue.getValue());
        if (collection.contains(actualTableName)){
            return actualTableName;
        }
        throw new IllegalArgumentException();
    }
}
