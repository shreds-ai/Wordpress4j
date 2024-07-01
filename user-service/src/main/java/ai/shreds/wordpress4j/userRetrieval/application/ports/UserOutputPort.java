package ai.shreds.wordpress4j.userRetrieval.application.ports;

import ai.shreds.wordpress4j.userRetrieval.application.dtos.UserDTO;
import java.util.List;

/**
 * Defines the output operations for user data, facilitating the interaction between the application layer and external services or the user interface.
 */
public interface UserOutputPort {

    /**
     * Presents a single user's data in JSON format for web APIs or other formats for different interfaces.
     * @param user UserDTO containing user data
     */
    void presentUser(UserDTO user);

    /**
     * Presents a list of users' data in JSON format for web APIs or other suitable formats for different outputs.
     * @param users List of UserDTOs containing user data
     */
    void presentUsers(List<UserDTO> users);

    /**
     * Formats and presents errors consistently across different outputs, using the appropriate error messaging protocols.
     * @param e Exception to be handled
     */
    void handleError(Exception e);

    /**
     * Presents a paginated list of users' data, enhancing scalability and performance for large datasets.
     * @param page Current page number
     * @param size Number of users per page
     * @param users List of UserDTOs containing user data
     */
    void presentUsersPage(int page, int size, List<UserDTO> users);
}