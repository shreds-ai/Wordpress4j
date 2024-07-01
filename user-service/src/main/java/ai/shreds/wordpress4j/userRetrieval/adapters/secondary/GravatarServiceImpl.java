package ai.shreds.wordpress4j.userRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.userRetrieval.domain.exceptions.InvalidEmailException;
import ai.shreds.wordpress4j.userRetrieval.domain.ports.GravatarServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class GravatarServiceImpl implements GravatarServicePort {

    private static final Logger logger = LoggerFactory.getLogger(GravatarServiceImpl.class);
    private final Map<String, String> md5Cache = new HashMap<>();

    @Override
    public String computeMd5Hash(String email) {
        if (email == null || !validateEmailFormat(email)) {
            handleGravatarUrlException(new InvalidEmailException("Invalid email format"));
            return null;
        }
        return md5Cache.computeIfAbsent(email, this::computeMD5HashUsingMessageDigest);
    }

    private String computeMD5HashUsingMessageDigest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, '0');
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateGravatarUrl(String md5Hash, int size) {
        if (md5Hash == null || md5Hash.isEmpty() || size < 1 || size > 2048) {
            throw new IllegalArgumentException("Invalid parameters for Gravatar URL generation");
        }
        return String.format("http://2.gravatar.com/avatar/%s?s=%d&d=identicon", md5Hash, size);
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
}