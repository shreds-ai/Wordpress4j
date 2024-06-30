package com.example.wordpressclone.domain.ports;

import com.example.wordpressclone.domain.entities.UserEntity;
import com.example.wordpressclone.domain.entities.UserMetaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import java.sql.SQLException;

/**
 * Interface defining the contract for user data access.
 * It declares methods for fetching users and user metadata from the database.
 */
public interface UserRepositoryPort {

    /**
     * Retrieves a list of all users from the database.
     * @return a list of UserEntity
     */
    List<UserEntity> findAllUsers();

    /**
     * Finds a single user by their ID.
     * @param id the ID of the user
     * @return an Optional containing the found UserEntity or empty if not found
     */
    Optional<UserEntity> findUserById(Long id);

    /**
     * Retrieves a list of metadata entries associated with a given user ID.
     * @param userId the ID of the user whose metadata is to be retrieved
     * @return a list of UserMetaEntity
     */
    List<UserMetaEntity> findUserMetadataByUserId(Long userId);

    /**
     * Handles exceptions specifically related to database operations, providing a centralized error handling mechanism within the interface.
     * @param e the exception to handle
     * Differentiate handling based on exception types like SQLException and DataAccessException.
     */
    void handleDatabaseException(Exception e);
}