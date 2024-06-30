package com.example.wordpressclone.adapters.primary;

import com.example.wordpressclone.application.dtos.UserDTO;
import com.example.wordpressclone.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Min;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.example.wordpressclone.domain.exceptions.DatabaseTimeoutException;
import com.example.wordpressclone.domain.exceptions.UserNotFoundException;
import com.example.wordpressclone.domain.exceptions.InvalidUserIdException;

@RestController
@RequestMapping("/users")
public class UserAdapter {

    private static final Logger logger = LoggerFactory.getLogger(UserAdapter.class);
    private final UserService userService;

    @Autowired
    public UserAdapter(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.listUsers();
            return ResponseEntity.ok(users);
        } catch (DataAccessException | DatabaseTimeoutException e) {
            logger.error("Database error: ", e);
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable @Min(1) Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (InvalidUserIdException | UserNotFoundException e) {
            logger.error("User not found or invalid ID: ", e);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException | DatabaseTimeoutException e) {
            logger.error("Database error: ", e);
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}