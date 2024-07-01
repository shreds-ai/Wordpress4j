package ai.shreds.wordpress4j.commentRetrieval.infrastructure.external_services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GravatarClient {

    private static final Logger logger = LoggerFactory.getLogger(GravatarClient.class);

    protected MessageDigest messageDigest;
    protected String gravatarBaseUrl;

    public GravatarClient(MessageDigest md, @Value("${gravatar.base.url}") String baseUrl) {
        this.messageDigest = md;
        this.gravatarBaseUrl = baseUrl;
    }

    public String getAvatarUrl(String email, int size) {
        if (email == null || email.isEmpty()) {
            logger.error("Email is null or empty");
            return null;
        }
        if (!isValidEmail(email)) {
            logger.warn("Invalid email format: {}", email);
            return null;
        }
        String hash = computeMD5Hash(email);
        return String.format("%s/avatar/%s?s=%d&d=mm&r=g", gravatarBaseUrl, hash, size);
    }

    private String computeMD5Hash(String email) {
        try {
            messageDigest.reset();
            messageDigest.update(email.getBytes());
            byte[] digest = messageDigest.digest();
            Formatter formatter = new Formatter();
            for (byte b : digest) {
                formatter.format("%02x", b);
            }
            String hash = formatter.toString();
            formatter.close();
            return hash;
        } catch (RuntimeException e) {
            logger.error("Error computing MD5 hash", e);
            throw e;
        }
    }

    protected boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,8}");
    }
}