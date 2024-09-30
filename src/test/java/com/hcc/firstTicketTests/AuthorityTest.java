package com.hcc.firstTicketTests;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorityTest {

    @Test
    public void testAuthorityEntity() {
        // Arrange
        User user = new User();
        Authority authority = new Authority("ROLE_LEARNER");
        authority.setUser(user);

        // Assert
        assertEquals("ROLE_LEARNER", authority.getAuthority());
        assertEquals(user, authority.getUser());
    }
}