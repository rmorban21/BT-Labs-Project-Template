package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

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
    }
}
