package com.wordpressclone.tags.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "wordpressclone.tags")
@Data
public class TagConfigurationProperties {

    private static final Logger logger = LoggerFactory.getLogger(TagConfigurationProperties.class);

    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    public void loadEnvironmentConfig(String env) {
        if (env == null || env.trim().isEmpty()) {
            throw new IllegalArgumentException("Environment must not be null or empty");
        }
        try {
            logger.info("Loading configuration for environment: {}", env);
        } catch (Exception e) {
            logger.error("Error loading configuration for environment: {}", env, e);
            throw new RuntimeException("Failed to load environment config", e);
        }
    }

    public void validateConfigProperties() {
        try {
            logger.info("Validating configuration properties.");
        } catch (Exception e) {
            logger.error("Configuration validation failed", e);
            throw new RuntimeException("Configuration validation error", e);
        }
    }

    public void reloadConfiguration() {
        logger.info("Reloading configuration settings.");
    }
}