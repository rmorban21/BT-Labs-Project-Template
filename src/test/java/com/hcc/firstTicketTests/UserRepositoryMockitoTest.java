package com.hcc.firstTicketTests;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        // Act
        User foundUser = userRepository.findByUsername("testUser");

        // Assert
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testFindByUsername_UserNotExists() {
        // Arrange
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        // Act
        User foundUser = userRepository.findByUsername("nonExistentUser");

        // Assert
        assertNull(foundUser);
    }
}
