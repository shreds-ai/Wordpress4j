package com.example.wordpressclone.domain.ports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface for Gravatar URL generation services.
 */
public interface GravatarServicePort {

    Logger logger = LoggerFactory.getLogger(GravatarServicePort.class);

    /**
     * Computes the MD5 hash of the given email address after validating the email format.
     * @param email the email address to hash
     * @return the MD5 hash of the email
     * @throws IllegalArgumentException if email is null or empty
     */
    String computeMd5Hash(String email);

    /**
     * Generates a Gravatar URL using the provided MD5 hash and the specified size.
     * @param md5Hash the MD5 hash of the user's email
     * @param size the size of the avatar in pixels
     * @return the generated Gravatar URL
     * @throws IllegalArgumentException if md5Hash is null or empty, or size is non-positive
     */
    String generateGravatarUrl(String md5Hash, int size);

    /**
     * Validates the format of the email to ensure it adheres to standard email formatting rules.
     * @param email the email to validate
     * @return true if the email format is valid, false otherwise
     * @throws IllegalArgumentException if email is null or empty
     */
    boolean validateEmailFormat(String email);

    /**
     * Handles exceptions that may occur during the generation of Gravatar URLs.
     * Specific exceptions like IllegalArgumentException or IOException are caught for detailed error handling.
     * Additionally, logs the exception details for better debugging and maintenance.
     * @param e the exception to handle
     */
    void handleGravatarUrlException(Exception e);
}