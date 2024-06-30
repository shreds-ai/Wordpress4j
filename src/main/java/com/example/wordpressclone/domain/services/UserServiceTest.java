package com.example.wordpressclone.domain.services;

import com.example.wordpressclone.domain.entities.UserDTO;
import com.example.wordpressclone.domain.ports.GravatarServicePort;
import com.example.wordpressclone.domain.ports.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private UserRepositoryPort userRepositoryPortMock;
    private GravatarServicePort gravatarServicePortMock;

    @BeforeEach
    void setUp() {
        userRepositoryPortMock = Mockito.mock(UserRepositoryPort.class);
        gravatarServicePortMock = Mockito.mock(GravatarServicePort.class);
        userService = new UserService(userRepositoryPortMock, gravatarServicePortMock);
    }

    @Test
    @DisplayName("Test Retrieve User by ID")
    public void testRetrieveUserById() {
        Long userId = 1L;
        UserDTO expectedUser = new UserDTO(userId, "Test User", "http://example.com", "Description", "http://localhost:8090/author/testuser/", "testuser", Collections.emptyMap());
        Mockito.when(userRepositoryPortMock.findUserById(userId)).thenReturn(Optional.of(new UserEntity(userId, "Test User", "testuser", "http://example.com", "testuser@example.com")));
        Mockito.when(gravatarServicePortMock.generateGravatarUrl(Mockito.anyString(), Mockito.eq(24))).thenReturn("http://gravatar.com/avatar/example?s=24");

        UserDTO actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Test Retrieve All Users")
    public void testRetrieveAllUsers() {
        List<UserDTO> expectedUsers = Collections.singletonList(new UserDTO(1L, "Test User", "http://example.com", "Description", "http://localhost:8090/author/testuser/", "testuser", Collections.emptyMap()));
        Mockito.when(userRepositoryPortMock.findAllUsers()).thenReturn(Collections.singletonList(new UserEntity(1L, "Test User", "testuser", "http://example.com", "testuser@example.com")));

        List<UserDTO> actualUsers = userService.listUsers();

        assertNotNull(actualUsers);
        assertFalse(actualUsers.isEmpty());
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    @DisplayName("Test User Not Found")
    public void testUserNotFound() {
        Long userId = 999L;
        Mockito.when userRepo... (truncated for brevity)