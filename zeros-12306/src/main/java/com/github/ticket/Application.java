package com.github.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    // Part3 车站管理
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
