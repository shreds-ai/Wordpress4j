package com.example.wordpressclone.util;

import org.slf4j.Logger;
import com.example.wordpressclone.logging.CentralizedLogger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;

public class ErrorHandlingUtils {

    private static final Logger logger = CentralizedLogger.getLogger(ErrorHandlingUtils.class);

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
            logger.error("Non-recoverable exception occurred: " + e.getMessage() + \