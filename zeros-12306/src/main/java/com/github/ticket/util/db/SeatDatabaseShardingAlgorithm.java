package com.github.ticket.util.db;

import java.util.Collection;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
public class SeatDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {


    private final static String PREFIX = "trainSeatDB";

    private String determineDB(int val) {
        int db = val % 5;
        if (db == 0) {
            db = 5;
        }
        return PREFIX + db;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        String actualDBName = determineDB(preciseShardingValue.getValue());
        if (collection.contains(actualDBName)){
            return actualDBName;
        }
        throw new IllegalArgumentException();
    }
}
