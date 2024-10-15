package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUser(Authentication authentication) {
        String username = authentication.getName(); // Get the logged-in user's username
        List<Assignment> assignments = assignmentService.getAssignmentsByUser(username);
        return ResponseEntity.ok(assignments);
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