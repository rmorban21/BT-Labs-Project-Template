package com.hcc.firstTicketTests;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    @Test
    public void testAssignmentEntity() {
        // Arrange
        User user = new User();
        Assignment assignment = new Assignment();
        assignment.setStatus("Submitted");
        assignment.setNumber(1);
        assignment.setGithubUrl("https://github.com/test/repo");
        assignment.setBranch("main");
        assignment.setUser(user);

        // Assert
        assertEquals("Submitted", assignment.getStatus());
        assertEquals(1, assignment.getNumber());
        assertEquals("https://github.com/test/repo", assignment.getGithubUrl());
        assertEquals("main", assignment.getBranch());
        assertEquals(user, assignment.getUser());
    }
}