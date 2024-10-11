package com.hcc.firstTicketTests;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryMockitoTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testFindByUsername_UserExists() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Mock the repository to return an Optional containing the user
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userRepository.findByUsername("testUser");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
    }

    @Test
    public void testFindByUsername_UserNotExists() {
        // Mock the repository to return an empty Optional
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        // Act
        Optional<User> foundUser = userRepository.findByUsername("nonExistentUser");

        // Assert
        assertFalse(foundUser.isPresent());
    }
}
