package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Fetch assignments for a specific user
    public List<Assignment> getAssignmentsByUser(User user) {
        return assignmentRepository.findByUser(user);
    }

    // Fetch a specific assignment by ID
    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
    }

    // Update an assignment by ID
    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment assignment = getAssignmentById(id);
        assignment.setStatus(updatedAssignment.getStatus());
        assignment.setGithubUrl(updatedAssignment.getGithubUrl());
        assignment.setBranch(updatedAssignment.getBranch());
        assignment.setReviewVideoUrl(updatedAssignment.getReviewVideoUrl());
        return assignmentRepository.save(assignment);
    }

    // Create a new assignment
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}
