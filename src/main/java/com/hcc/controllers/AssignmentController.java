package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
<<<<<<< HEAD
=======
import com.hcc.entities.User;
>>>>>>> secondticket
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;

=======
>>>>>>> secondticket
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

<<<<<<< HEAD
    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUser(Authentication authentication) {
        String username = authentication.getName(); // Get the logged-in user's username
        List<Assignment> assignments = assignmentService.getAssignmentsByUser(username);
        return ResponseEntity.ok(assignments);
=======
    @Autowired
    private UserService userService;

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponseDto>> getAssignmentsByUser(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        List<Assignment> assignments = assignmentService.getAssignmentsByUser(user);
        List<AssignmentResponseDto> response = assignments.stream()
                .map(AssignmentResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        AssignmentResponseDto response = new AssignmentResponseDto(assignment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponseDto> updateAssignmentById(
            @PathVariable Long id, @RequestBody Assignment updatedAssignment) {
        Assignment assignment = assignmentService.updateAssignmentById(id, updatedAssignment);
        AssignmentResponseDto response = new AssignmentResponseDto(assignment);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assignments")
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @RequestBody Assignment newAssignment, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        newAssignment.setUser(user);
        Assignment createdAssignment = assignmentService.createAssignment(newAssignment);
        AssignmentResponseDto response = new AssignmentResponseDto(createdAssignment);
        return ResponseEntity.ok(response);
>>>>>>> secondticket
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment updatedAssignment) {
        Assignment assignment = assignmentService.updateAssignment(id, updatedAssignment);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment newAssignment, Authentication authentication) {
        String username = authentication.getName();
        Assignment createdAssignment = assignmentService.createAssignment(newAssignment, username);
        return ResponseEntity.ok(createdAssignment);
    }

}