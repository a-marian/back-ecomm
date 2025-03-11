package com.back.ecomm.controller;

import com.back.ecomm.entity.User;
import com.back.ecomm.record.UserRecord;
import com.back.ecomm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserController userController;

    private UserRecord validUserRecord;

    @BeforeEach
    void setUp() {
        validUserRecord = new UserRecord("testuser", "test@example.com", "password123");
    }

    @Test
    void register_WhenUserDoesNotExist_ShouldRegisterSuccessfully() {
        // Arrange
        when(userService.existsUser(validUserRecord.username())).thenReturn(false);
        when(userService.save(any(UserRecord.class))).thenReturn(new User());

        // Act
        ResponseEntity<String> response = userController.register(validUserRecord, request);

        // Assert
        assertEquals("user registered", response.getBody());
        verify(userService).existsUser(validUserRecord.username());
        verify(userService).save(any(UserRecord.class));
        verify(eventPublisher).publishEvent(any());
    }

    @Test
    void register_WhenUserExists_ShouldReturnAlreadyRegistered() {
        // Arrange
        when(userService.existsUser(validUserRecord.username())).thenReturn(true);

        // Act
        ResponseEntity<String> response = userController.register(validUserRecord, request);

        // Assert
        assertEquals("User already registered", response.getBody());
        verify(userService).existsUser(validUserRecord.username());
        verify(userService, never()).save(any(UserRecord.class));
        verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    void register_WhenSaveFails_ShouldReturnError() {
        // Arrange
        when(userService.existsUser(validUserRecord.username())).thenReturn(false);
        when(userService.save(any(UserRecord.class))).thenReturn(null);

        // Act
        ResponseEntity<String> response = userController.register(validUserRecord, request);

        // Assert
        assertEquals("user not registered, try again", response.getBody());
        verify(userService).existsUser(validUserRecord.username());
        verify(userService).save(any(UserRecord.class));
        verify(eventPublisher, never()).publishEvent(any());
    }
} 