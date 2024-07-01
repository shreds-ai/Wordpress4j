package ai.shreds.wordpress4j.userRetrieval.application.ports;

import ai.shreds.wordpress4j.userRetrieval.application.dtos.UserDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface defining the input operations for user data processing.
 * This interface is crucial for decoupling the core logic from data access and external interfaces.
 */
public interface UserInputPort {

    Logger logger = LoggerFactory.getLogger(UserInputPort.class);

    /**
     * Processes the retrieval of a user by their ID.
     * @param userId the ID of the user to retrieve
     * @return UserDTO containing user data
     * This method fetches user data based on the user ID.
     */
    @Transactional
    UserDTO processUserRetrieval(Long userId);

    /**
     * Processes the retrieval of all users.
     * @return List of UserDTOs containing data of all users
     * This method fetches data for all users in the database.
     */
    @Transactional
    List<UserDTO> processAllUsersRetrieval();

    /**
     * Validates the user input data.
     * @param userDTO the user data to validate
     * @return true if the input is valid, false otherwise
     * This method checks the integrity and validity of user data before processing.
     */
    boolean validateUserInput(@Valid @NotNull UserDTO userDTO);

    /**
     * Handles exceptions during user data processing.
     * @param e the exception to handle
     * This method logs and handles exceptions that may occur during user data processing.
     */
    default void handleInputException(Exception e) {
        logger.error("Error processing user data", e);
        throw new RuntimeException("Error processing user data", e);
    }

    /**
     * Sanitizes user input data to prevent security vulnerabilities.
     * @param userDTO the user data to sanitize
     * @return sanitized UserDTO
     * This method ensures that user data is free from security threats before processing.
     */
    UserDTO sanitizeUserInput(UserDTO userDTO);
}