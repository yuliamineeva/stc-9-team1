package ru.innopolis.stc9.t1;

import org.apache.log4j.Logger;

public class ErrorMsgHandler {
    private final static Logger logger = Logger.getLogger(ErrorMsgHandler.class);
    private static String message;

    public static String getMessage() {
        String result = message;
        message = null;
        return result;
    }

    public static void setMessage(String description) {
        logger.error(description);
        ErrorMsgHandler.message = description;
    }

    public static void setMessage(String description, Throwable error) {
        logger.error(description, error);
        ErrorMsgHandler.message = description + ": " + error;
    }
}
