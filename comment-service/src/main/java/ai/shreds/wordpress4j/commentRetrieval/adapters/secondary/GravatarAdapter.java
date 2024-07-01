package ai.shreds.wordpress4j.commentRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.commentRetrieval.infrastructure.external_services.GravatarClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GravatarAdapter extends GravatarClient {

    private static final Logger logger = LoggerFactory.getLogger(GravatarAdapter.class);
    private Map<String, String> cache = new ConcurrentHashMap<>();

    public GravatarAdapter() throws NoSuchAlgorithmException {
        super(MessageDigest.getInstance("MD5"), "https://www.gravatar.com");
        logger.info("GravatarAdapter initialized with MD5 MessageDigest instance.");
    }

    @Override
    public String getAvatarUrl(String email, int size) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (!(size == 24 || size == 48 || size == 96) || !isValidEmail(email)) {
            logger.error("Invalid input parameters.");
            throw new IllegalArgumentException("Size must be one of the predefined values and email must be valid.");
        }
        return cache.computeIfAbsent(email + size, k -> {
            String hash = computeMD5Hash(email);
            return String.format("%s/avatar/%s?s=%d&d=mm&r=g", gravatarBaseUrl, hash, size);
        });
    }

    private String computeMD5Hash(String email) {
        if (!isValidEmail(email)) {
            logger.error("Invalid email format: {}", email);
            throw new IllegalArgumentException("Invalid email format");
        }
        messageDigest.reset();
        messageDigest.update(email.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public Map<String, String> fetchAvatarUrls(String email) {
        Map<String, String> avatarUrls = new HashMap<>();
        avatarUrls.put("24", getAvatarUrl(email, 24));
        avatarUrls.put("48", getAvatarUrl(email, 48));
        avatarUrls.put("96", getAvatarUrl(email, 96));
        return avatarUrls;
    }
}