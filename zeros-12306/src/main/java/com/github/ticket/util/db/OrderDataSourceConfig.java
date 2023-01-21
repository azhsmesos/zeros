package com.github.ticket.util.db;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@Configuration
@MapperScan(basePackages = "com.github.ticket.dao.orderDAO")
public class OrderDataSourceConfig {

    @Bean(name = DataSources.TRAIN_ORDER_DB)
    @ConfigurationProperties(prefix = "spring.datasource-order")
    public DataSource orderDB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderTransactionManger")
    public DataSourceTransactionManager orderTransactionManager(
            @Qualifier(DataSources.TRAIN_ORDER_DB) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "orderDBTemplate")
    public JdbcTemplate orderDB(@Qualifier(DataSources.TRAIN_ORDER_DB) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
