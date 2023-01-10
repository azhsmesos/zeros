package com.github.zeros;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("azh info");
        logger.debug("azh debug");
        logger.warn("azh warn");
        logger.error("azh error");
    }
}
