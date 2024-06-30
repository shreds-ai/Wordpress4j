package com.example.wordpressclone.configurations;

import com.example.wordpressclone.domain.ports.CategoryRepositoryPort;
import com.example.wordpressclone.domain.entities.CategoryEntity;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.apache.commons.configuration.ConfigurationException;

@Configuration
@EnableWebSecurity
public class CategoryConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CategoryConfiguration.class);
    private DataSource dataSource;

    @Bean
    public CategoryRepositoryPort categoryRepositoryPort() {
        return new CategoryRepositoryPort() {
            @Override
            public List<CategoryEntity> findAllCategories() {
                logger.info("Fetching all categories");
                return new ArrayList<>();
            }

            @Override
            public Optional<CategoryEntity> findCategoryById(Long id) {
                logger.info("Fetching category by ID: {}", id);
                return Optional.empty();
            }

            @Override
            public Optional<CategoryEntity> findCategoryByName(String name) {
                logger.info("Fetching category by name: {}", name);
                return Optional.empty();
            }
        };
    }

    public void configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/categories/**").hasRole("ADMIN")
            .and()
            .httpBasic();
        logger.info("Security configuration completed.");
    }

    public void configureDataSourceProperties() throws ConfigurationException {
        try {
            logger.info("Configuring data source properties");
        } catch (Exception e) {
            logger.error("Failed to configure DataSource", e);
        }
    }
}
