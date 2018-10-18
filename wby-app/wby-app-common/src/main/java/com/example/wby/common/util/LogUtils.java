package com.example.wby.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {

    public static void logException(Exception e) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuilder logInfo = new StringBuilder(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logInfo.append(" Exception:");
        logInfo.append(trace.toString());
        Logger logger;
        try {
            logger = LoggerFactory.getLogger(Class.forName(newClass));
            logger.error(logInfo.toString());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void logError(String message, Object... args) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuilder logInfo = new StringBuilder(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        logInfo.append(" INFO:");
        message = getString(message, args);
        logInfo.append(message);
        Logger logger;
        try {
            logger = LoggerFactory.getLogger(Class.forName(newClass));
            logger.error(logInfo.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getString(String message, Object[] args) {
        for (Object arg : args) {
            String[] strArr = message.split("\\{}", 2);
            if (arg == null) {
                arg = "null";
            }
            message = strArr[0] + String.valueOf(arg) + strArr[1];
        }
        return message;
    }


}
