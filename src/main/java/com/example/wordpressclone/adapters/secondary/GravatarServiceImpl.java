package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.domain.ports.GravatarServicePort;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class GravatarServiceImpl implements GravatarServicePort {

    private static final Logger logger = LoggerFactory.getLogger(GravatarServiceImpl.class);
    private Map<String, String> md5Cache = new HashMap<>();

    @Override
    public String computeMd5Hash(String email) {
        if (!validateEmailFormat(email)) {
            handleGravatarUrlException(new IllegalArgumentException("Invalid email format"));
            return null;
        }
        return md5Cache.computeIfAbsent(email, k -> DigestUtils.md5Hex(k));
    }

    @Override
    public String generateGravatarUrl(String md5Hash, int size) {
        if (size < 1 || size > 2048) {
            throw new IllegalArgumentException("Size parameter out of range");
        }
        return String.format("http://www.gravatar.com/avatar/%s?s=%d&d=identicon", md5Hash, size);
    }

    @Override
    public boolean validateEmailFormat(String email) {
        boolean isValid = email != null && email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        logger.info("Validating email format for: {}, Result: {}", email, isValid);
        return isValid;
    }

    @Override
    public void handleGravatarUrlException(Exception e) {
        logger.error("Error generating Gravatar URL", e);
    }

    public String generateGravatarUrl(String email, int size) throws IllegalArgumentException, NullPointerException {
        logger.info("Starting Gravatar URL generation for email: {}", email);
        if (email == null) throw new NullPointerException("Email cannot be null");
        if (!validateEmailFormat(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        String md5Hash = computeMd5Hash(email);
        return generateGravatarUrl(md5Hash, size);
    }
}