package com.example.wordpressclone.domain.value_objects;

import lombok.Data;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class UserValue {
    private static final Logger logger = LoggerFactory.getLogger(UserValue.class);
    private Long userId;
    private String userLogin;
    private String userNicename;
    private String userEmail;
    private String displayName;

    public boolean validateUserDetails() {
        logger.debug("Validating user details for: " + userId);
        if (userId == null) throw new IllegalArgumentException("User ID cannot be null");
        if (userLogin == null) throw new IllegalArgumentException("User login cannot be null");
        if (userNicename == null) throw new IllegalArgumentException("User nicename cannot be null");
        if (userEmail == null) throw new IllegalArgumentException("User email cannot be null");
        if (displayName == null) throw new IllegalArgumentException("Display name cannot be null");
        return true;
    }

    public String transformUserDisplayName(String displayName) {
        logger.debug("Transforming display name: " + displayName);
        if (displayName == null || displayName.isEmpty()) {
            return "";
        }
        StringBuilder newName = new StringBuilder();
        String[] words = displayName.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                newName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return newName.toString().trim();
    }

    public String standardizeEmailFormat(String email) {
        logger.debug("Standardizing email format for: " + email);
        if (email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            return "";
        }
        return email.trim().toLowerCase();
    }

    public String computeEmailHash(String email) {
        logger.debug("Computing email hash for: " + email);
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(email.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 hashing algorithm not available", e);
        }
    }
}