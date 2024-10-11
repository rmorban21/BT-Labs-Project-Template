package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository directly

    // Create a new assignment
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment newAssignment = assignmentService.createAssignment(assignment);
        return ResponseEntity.ok(newAssignment);
    }
    // Get all assignments by logged-in user
    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUser() {
        // Get the logged-in user's details from the security context
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Fetch the User entity using the username from UserRepository
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch and return assignments for this user
        List<Assignment> assignments = assignmentService.getAssignmentsByUser(user);
        return ResponseEntity.ok(assignments);
    }
}
