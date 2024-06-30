package com.example.wordpressclone.adapters.primary;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.example.wordpressclone.domain.services.UserService;
import com.example.wordpressclone.application.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.example.wordpressclone.domain.exceptions.UserNotFoundException;
import com.example.wordpressclone.domain.exceptions.DatabaseTimeoutException;

@RestController
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.listUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Internal server error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@NotNull @PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            logger.error("User not found for ID: " + id, e);
            return ResponseEntity.notFound().build();
        } catch (DatabaseTimeoutException e) {
            logger.error("Database timeout error while retrieving user with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(null);
        } catch (Exception e) {
            logger.error("Internal server error while retrieving user with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}