package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.domain.ports.GravatarServicePort;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import com.example.wordpressclone.adapters.secondary.InvalidEmailException;

public class GravatarServiceImpl implements GravatarServicePort {

    private static final Logger logger = LoggerFactory.getLogger(GravatarServiceImpl.class);
    private Map<String, String> md5Cache = new HashMap<>();

    @Override
    public String computeMd5Hash(String email) {
        if (email == null || !validateEmailFormat(email)) {
            handleGravatarUrlException(new IllegalArgumentException("Invalid email format"));
            return null;
        }
        return md5Cache.computeIfAbsent(email, k -> DigestUtils.md5Hex(k));
    }

    @Override
    public String generateGravatarUrl(String md5Hash, int size) {
        if (md5Hash == null || md5Hash.isEmpty() || size < 1 || size > 2048) {
            throw new IllegalArgumentException("Invalid parameters for Gravatar URL generation");
        }
        return String.format("http://www.gravatar.com/avatar/%s?s=%d&d=identicon", md5Hash, size);
    }

    @Override
    public boolean validateEmailFormat(String email) {
        boolean isValid = email != null && email.matches("[A-Za-z0-9._%+-]+@[A-Zaiz0-9.-]+\\.[A-Zaiz]{2,6}");
        logger.info("Validating email format for: {}, Result: {}", email, isValid);
        return isValid;
    }

    @Override
    public void handleGravatarUrlException(Exception e) {
        logger.error("Error generating Gravatar URL", e);
    }
}