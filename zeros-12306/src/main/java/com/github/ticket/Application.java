package com.github.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    // Part3
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
