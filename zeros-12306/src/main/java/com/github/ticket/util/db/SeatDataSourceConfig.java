package com.github.ticket.util.db;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.google.common.collect.Maps;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@Configuration
@MapperScan(basePackages = "com.github.ticket.dao.seatDAO")
public class SeatDataSourceConfig {

    @Bean(name = DataSources.TRAIN_SEAT_DB_1)
    @ConfigurationProperties(prefix = "spring.datasource-seat-one")
    public DataSource seatDB1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSources.TRAIN_SEAT_DB_2)
    @ConfigurationProperties(prefix = "spring.datasource-seat-two")
    public DataSource seatDB2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSources.TRAIN_SEAT_DB_3)
    @ConfigurationProperties(prefix = "spring.datasource-seat-three")
    public DataSource seatDB3() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSources.TRAIN_SEAT_DB_4)
    @ConfigurationProperties(prefix = "spring.datasource-seat-four")
    public DataSource seatDB4() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSources.TRAIN_SEAT_DB_5)
    @ConfigurationProperties(prefix = "spring.datasource-seat-five")
    public DataSource seatDB5() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "trainSeatShardingDataSource")
    public DataSource trainSeatShardingDataSource(
            @Qualifier(DataSources.TRAIN_SEAT_DB_1) DataSource seatDBOne,
            @Qualifier(DataSources.TRAIN_SEAT_DB_2) DataSource seatDBTwo,
            @Qualifier(DataSources.TRAIN_SEAT_DB_3) DataSource seatDBThree,
            @Qualifier(DataSources.TRAIN_SEAT_DB_4) DataSource seatDBFour,
            @Qualifier(DataSources.TRAIN_SEAT_DB_5) DataSource seatDBFive) throws SQLException {
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();
        // 分库映射
        Map<String, DataSource> sourceMap = Maps.newHashMap();
        sourceMap.put(DataSources.TRAIN_SEAT_DB_1, seatDBOne);
        sourceMap.put(DataSources.TRAIN_SEAT_DB_2, seatDBTwo);
        sourceMap.put(DataSources.TRAIN_SEAT_DB_3, seatDBThree);
        sourceMap.put(DataSources.TRAIN_SEAT_DB_4, seatDBFour);
        sourceMap.put(DataSources.TRAIN_SEAT_DB_5, seatDBFive);
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        tableRuleConfiguration.setLogicTable("train_seat");
        tableRuleConfiguration.setActualDataNodes(DataSources.TRAIN_SEAT_DB_1 + ".train_seat_1," +
                DataSources.TRAIN_SEAT_DB_2 + ".train_seat_2," +
                DataSources.TRAIN_SEAT_DB_3 + ".train_seat_3," +
                DataSources.TRAIN_SEAT_DB_4 + ".train_seat_4," +
                DataSources.TRAIN_SEAT_DB_5 + ".train_seat_5," +
                DataSources.TRAIN_SEAT_DB_1 + ".train_seat_6," +
                DataSources.TRAIN_SEAT_DB_2 + ".train_seat_7," +
                DataSources.TRAIN_SEAT_DB_3 + ".train_seat_8," +
                DataSources.TRAIN_SEAT_DB_4 + ".train_seat_9," +
                DataSources.TRAIN_SEAT_DB_5 + ".train_seat_10");
        // 设置分库策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(
                "train_number_id", new SeatDatabaseShardingAlgorithm()));
        // 设置分表策略
        tableRuleConfiguration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(
                "train_number_id", new SeatTableShardingAlgorithm()));
        configuration.getTableRuleConfigs().add(tableRuleConfiguration);
        return ShardingDataSourceFactory.createDataSource(sourceMap, configuration, new ConcurrentHashMap<>(),
                new Properties());
    }

    @Bean(name = "trainSeatTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("trainSeatShardingDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "seatDBTemplate")
    public JdbcTemplate seatDB(@Qualifier("trainSeatShardingDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
