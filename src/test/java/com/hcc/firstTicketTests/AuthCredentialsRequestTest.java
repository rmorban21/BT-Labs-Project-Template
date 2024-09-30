package com.hcc.firstTicketTests;
import com.hcc.dto.AuthCredentialsRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthCredentialsRequestTest {

    @Test
    public void testAuthCredentialsRequest() {
        // Arrange
        AuthCredentialsRequest authCredentialsRequest = new AuthCredentialsRequest();
        authCredentialsRequest.setUsername("testUser");
        authCredentialsRequest.setPassword("password123");

        // Assert
        assertEquals("testUser", authCredentialsRequest.getUsername());
        assertEquals("password123", authCredentialsRequest.getPassword());
    }
}