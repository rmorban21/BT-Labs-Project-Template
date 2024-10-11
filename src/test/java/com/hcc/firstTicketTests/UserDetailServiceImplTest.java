package com.hcc.firstTicketTests;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.services.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailServiceImpl.loadUserByUsername("nonExistentUser");
        });
    }
}