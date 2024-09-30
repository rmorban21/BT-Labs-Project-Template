package com.hcc.firstTicketTests;
import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentResponseDtoTest {

    @Test
    public void testAssignmentResponseDto() {
        User user = new User();
        Assignment assignment = new Assignment("Submitted", 1, "https://github.com/test/repo", "main", "https://test.com/video", user, null);

        AssignmentResponseDto dto = new AssignmentResponseDto(assignment);

        assertNotNull(dto);
        assertEquals(assignment.getId(), dto.getId());
        assertEquals(assignment.getStatus(), dto.getStatus());
        assertEquals(assignment.getNumber(), dto.getNumber());
        assertEquals(assignment.getGithubUrl(), dto.getGithubUrl());
        assertEquals(assignment.getBranch(), dto.getBranch());
        assertEquals(assignment.getReviewVideoUrl(), dto.getReviewVideoUrl());
    }
}
