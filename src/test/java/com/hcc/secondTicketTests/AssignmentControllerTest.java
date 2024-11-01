package com.hcc.secondTicketTests;

import com.hcc.controllers.AssignmentController;
import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentControllerTest {

    @Mock
    private AssignmentService assignmentService;

    @Mock
    private UserService userService;

    @Mock
    private Principal principal;

    @InjectMocks
    private AssignmentController assignmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAssignmentsByUser() {
        User mockUser = new User();
        mockUser.setUsername("testUser");

        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        List<Assignment> assignments = List.of(assignment1, assignment2);

        when(principal.getName()).thenReturn("testUser");
        when(userService.findUserByUsername("testUser")).thenReturn(mockUser);
        when(assignmentService.getAssignmentsByUser(mockUser)).thenReturn(assignments);

        ResponseEntity<List<AssignmentResponseDto>> response = assignmentController.getAssignmentsByUser(principal);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetAssignmentById() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);

        when(assignmentService.getAssignmentById(1L)).thenReturn(assignment);

        ResponseEntity<AssignmentResponseDto> response = assignmentController.getAssignmentById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testUpdateAssignmentById() {
        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setId(1L);
        updatedAssignment.setStatus("Completed");

        when(assignmentService.updateAssignmentById(eq(1L), any(Assignment.class))).thenReturn(updatedAssignment);

        Assignment assignmentUpdate = new Assignment();
        assignmentUpdate.setStatus("Completed");

        ResponseEntity<AssignmentResponseDto> response = assignmentController.updateAssignmentById(1L, assignmentUpdate);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Completed", response.getBody().getStatus());
    }

    @Test
    public void testCreateAssignment() {
        User mockUser = new User();
        mockUser.setUsername("testUser");

        Assignment newAssignment = new Assignment();
        newAssignment.setStatus("Pending");

        when(principal.getName()).thenReturn("testUser");
        when(userService.findUserByUsername("testUser")).thenReturn(mockUser);
        when(assignmentService.createAssignment(any(Assignment.class))).thenReturn(newAssignment);

        ResponseEntity<AssignmentResponseDto> response = assignmentController.createAssignment(newAssignment, principal);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pending", response.getBody().getStatus());
    }
}
