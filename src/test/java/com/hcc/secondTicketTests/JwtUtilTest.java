package com.hcc.secondTicketTests;

import com.hcc.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setup() throws Exception {
        jwtUtil = new JwtUtil();

        // Use reflection to set the private `secret` field for testing
        Field secretField = JwtUtil.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(jwtUtil, "testSecretKey");  // Set secret for testing

        MockitoAnnotations.openMocks(this);

        // Mock UserDetails
        when(userDetails.getUsername()).thenReturn("testUser");
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
    }

    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("Generated Token: " + token);

        assertNotNull(token, "Token should not be null");
    }

    @Test
    public void testGetUsernameFromToken() {
        String token = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.getUsernameFromToken(token);

        System.out.println("Extracted Username: " + username);
        assertEquals("testUser", username, "Username should match the user in token");
    }

    @Test
    public void testGetExpirationDateFromToken() {
        String token = jwtUtil.generateToken(userDetails);
        Date expirationDate = jwtUtil.getExpirationDateFromToken(token);

        System.out.println("Expiration Date: " + expirationDate);
        assertNotNull(expirationDate, "Expiration date should not be null");
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken(userDetails);
        boolean isValid = jwtUtil.validateToken(token, userDetails);

        System.out.println("Is Token Valid: " + isValid);
        assertTrue(isValid, "Token should be valid for the given user details");
    }
}
