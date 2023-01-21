package com.github.ticket.util.db;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.assertj.core.util.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.google.common.collect.Maps;

import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@Configuration
@MapperScan(basePackages = "com.github.ticket.dao.baseDAO", sqlSessionTemplateRef = "")
public class DataSourceConfig {

    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource") // application.properteis中对应属性的前缀
    @Primary
    public DataSource dataSourceBase() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "resourceJdbcTemplate")
    public JdbcTemplate inditeJdbcTemplate(@Qualifier("baseDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = DataSources.MASTER_DB)
    @ConfigurationProperties(prefix = "spring.datasource-master")
    @Primary
    public DataSource masterDB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterDBTemplate")
    public JdbcTemplate masterDBTemplate(@Qualifier(DataSources.MASTER_DB) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = DataSources.SLAVE_DB)
    @ConfigurationProperties(prefix = "spring.datasource-slave")
    public DataSource slaveDB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSlaveDataSource")
    public DataSource masterSlaveDataSource(@Qualifier(DataSources.MASTER_DB) DataSource masterDataSource,
            @Qualifier(DataSources.SLAVE_DB) DataSource slaveDataSource) throws SQLException {
        Map<String, DataSource> map = Maps.newHashMap();
        map.put(DataSources.MASTER_DB, masterDataSource);
        map.put(DataSources.SLAVE_DB, slaveDataSource);
        MasterSlaveRuleConfiguration configuration =
                new MasterSlaveRuleConfiguration("ds_master_slave", DataSources.MASTER_DB,
                        Lists.newArrayList(DataSources.SLAVE_DB), new RoundRobinMasterSlaveLoadBalanceAlgorithm());
        return MasterSlaveDataSourceFactory.createDataSource(map, configuration, Maps.newHashMap(), new Properties());
    }

    @Primary
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier(DataSources.MASTER_DB) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
