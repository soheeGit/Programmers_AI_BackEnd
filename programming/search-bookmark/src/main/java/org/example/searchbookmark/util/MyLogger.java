package org.example.searchbookmark.util;

import java.util.logging.Logger;

public class MyLogger {
    final Logger logger;
    public MyLogger(String className) {
        logger = Logger.getLogger(className);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.severe(message);
    }
}
