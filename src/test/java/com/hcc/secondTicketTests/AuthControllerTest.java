package com.hcc.secondTicketTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.hcc.controllers.AuthController;
import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.dto.AuthResponse;
import com.hcc.utils.JwtUtil;
import com.hcc.services.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailServiceImpl userDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        AuthCredentialsRequest request = new AuthCredentialsRequest();
        request.setPassword("testUser");
        request.setUsername("password123");

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("mocked-jwt-token");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((AuthResponse) response.getBody()).getToken().contains("mocked-jwt-token"));
    }

    @Test
    void testLoginUnauthorized() {
        AuthCredentialsRequest request = new AuthCredentialsRequest();
        request.setUsername("invaliduser");
        request.setPassword("wrongPassword");

        doThrow(new UsernameNotFoundException("Unauthorized")).when(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        ResponseEntity<?> response = authController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized", response.getBody());
    }

    @Test
    void testValidateTokenSuccess() {
        String validToken = "valid-jwt-token";
        UserDetails userDetails = mock(UserDetails.class);
        when(jwtUtil.getUsernameFromToken(validToken)).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtUtil.validateToken(validToken, userDetails)).thenReturn(true);

        ResponseEntity<?> response = authController.validateToken("Bearer " + validToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Token is valid", response.getBody());
    }

    @Test
    void testValidateTokenInvalid() {
        String invalidToken = "invalid-jwt-token";
        when(jwtUtil.validateToken(eq(invalidToken), any())).thenReturn(false);

        ResponseEntity<?> response = authController.validateToken("Bearer " + invalidToken);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid Token", response.getBody());
    }
}
