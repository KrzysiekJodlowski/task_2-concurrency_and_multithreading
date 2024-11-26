package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.util;

import java.util.logging.*;

public class LoggerUtil {

    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Exception e) {
        logger.severe(message + ": " + e.getMessage());
    }
}
