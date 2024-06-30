package com.example.wordpressclone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;
import com.example.wordpressclone.adapters.exceptions.RecoverableException;
import java.lang.IllegalArgumentException;

public class ErrorHandlingUtils {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingUtils.class);

    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            logger.error("Regex syntax error in validateEmail for email: " + email, e);
            return false;
        }
    }

    public static void handleException(Exception e) {
        if (e instanceof RecoverableException) {
            logger.warn("Recoverable exception occurred: " + e.getMessage() + ", Timestamp: " + System.currentTimeMillis() + ", Thread: " + Thread.currentThread().getId(), e);
        } else {
            logger.error("Non-recoverable exception occurred: " + e.getMessage() + ", Timestamp: " + System.currentTimeMillis() + ", Thread: " + Thread.currentThread().getId(), e);
        }
    }

    public static void logErrorDetails(Exception e) {
        logger.error("Error details: ", e);
    }

    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}