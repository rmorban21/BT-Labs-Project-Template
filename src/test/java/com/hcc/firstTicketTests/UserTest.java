package com.hcc.firstTicketTests;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserEntity() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(AuthorityEnum.ROLE_LEARNER, new User()));

        User user = new User(LocalDate.now(), "testUser", "password123", authorities);

        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(1, user.getAuthoritiesList().size());
        assertEquals("ROLE_LEARNER", user.getAuthoritiesList().get(0).getAuthority());
    }
}
