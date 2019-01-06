package com.apache.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4Test {
    private static final Logger logger = LogManager.getLogger(Log4Test.class.getName());

    public static void main(String[] args) {
        logger.info("started");
    }



}
