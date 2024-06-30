package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.domain.entities.UserEntity;
import com.example.wordpressclone.domain.entities.UserMetaEntity;
import com.example.wordpressclone.domain.ports.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaRepository<UserEntity, Long> userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryAdapter.class);

    @Autowired
    public UserRepositoryAdapter(JpaRepository<UserEntity, Long> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAllUsers() {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Error accessing database", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (DataAccessException e) {
            logger.error("Error accessing database", e);
            throw e;
        }
    }

    @Override
    public List<UserMetaEntity> findUserMetadataByUserId(Long userId) {
        // Implement actual data fetching logic here
        // This should interact with a metadata repository or service
        return null; // Replace with actual call to metadata repository or service
    }

    @Override
    @Transactional
    public void handleDatabaseException(Exception e) {
        logger.error("Database exception occurred", e);
        // Log and handle the database exception appropriately
        // This is a placeholder for actual exception handling logic
    }
}