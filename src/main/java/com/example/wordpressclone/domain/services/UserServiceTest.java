package com.example.wordpressclone.domain.services;

import com.example.wordpressclone.application.dtos.UserDTO;
import com.example.wordpressclone.domain.entities.UserEntity;
import com.example.wordpressclone.domain.exceptions.UserNotFoundException;
import com.example.wordpressclone.domain.exceptions.DatabaseTimeoutException;
import com.example.wordpressclone.domain.exceptions.NetworkErrorException;
import com.example.wordpressclone.domain.exceptions.ServiceUnavailableException;
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
        Mockito.when(userRepositoryPortMock.findUserById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        assertTrue(exception instanceof UserNotFoundException);
    }

    @Test
    @DisplayName("Test Gravatar Url Generation")
    public void testGravatarUrlGeneration() {
        String email = "test@example.com";
        String expectedUrl = "http://gravatar.com/avatar/example?s=24";
        Mockito.when(gravatarServicePortMock.validateEmailFormat(email)).thenReturn(true);
        Mockito.when(gravatarServicePortMock.generateGravatarUrl(Mockito.anyString(), Mockito.eq(24))).thenReturn(expectedUrl);

        String actualUrl = userService.generateAvatarUrls(email);

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Test UserService Exception Handling")
    public void testUserServiceExceptionHandling() {
        Mockito.doThrow(new DatabaseTimeoutException("Database error")).when(userRepositoryPortMock).findAllUsers();

        Exception exception = assertThrows(DatabaseTimeoutException.class, () -> {
            userService.listUsers();
        });

        assertTrue(exception instanceof DatabaseTimeoutException);
    }

    @Test
    @DisplayName("Test Empty User Results")
    public void testEmptyUser Results() {
        Mockito.when(userRepositoryPortMock.findAllUsers()).thenReturn(Collections.emptyList());

        List<UserDTO> actualUsers = userService.listUsers();

        assertTrue(actualUsers.isEmpty());
    }

    @Test
    @DisplayName("Test Database Network Error Handling")
    public void testDatabaseNetworkErrorHandling() {
        Mockito.doThrow(new NetworkErrorException("Network error")).when(userRepositoryPortMock).findAllUsers();

        Exception exception = assertThrows(NetworkErrorException.class, () -> {
            userService.listUsers();
        });

        assertTrue(exception instanceof NetworkErrorException);
    }

    @Test
    @DisplayName("Test Gravatar Service Unavailability")
    public void testGravatarServiceUnavailability() {
        Mockito.doThrow(new ServiceUnavailableException("Service unavailable")).when(gravatarServicePortMock).generateGravatarUrl(Mockito.anyString(), Mockito.anyInt());

        Exception exception = assertThrows(ServiceUnavailableException.class, () -> {
            userService.generateAvatarUrls("test@example.com");
        });

        assertTrue(exception instanceof ServiceUnavailableException);
    }
}