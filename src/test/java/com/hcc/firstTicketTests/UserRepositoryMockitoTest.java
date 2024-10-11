package com.hcc.firstTicketTests;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User foundUser = userRepository.findByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testFindByUsername_UserNotExists() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        User foundUser = userRepository.findByUsername("nonExistentUser");

        assertNull(foundUser);
    }
}
