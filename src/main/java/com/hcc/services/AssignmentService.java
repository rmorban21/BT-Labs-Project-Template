package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Assignment> getAssignmentsByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return assignmentRepository.findByUser(user);
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment existingAssignment = assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found"));

        existingAssignment.setGithubUrl(updatedAssignment.getGithubUrl());
        existingAssignment.setBranch(updatedAssignment.getBranch());
        existingAssignment.setReviewVideoUrl(updatedAssignment.getReviewVideoUrl());
        existingAssignment.setStatus(updatedAssignment.getStatus());

        return assignmentRepository.save(existingAssignment);
    }

    public Assignment createAssignment(Assignment newAssignment, String username) {
        // Fetch the user from the repository based on the username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assign the fetched user to the new assignment
        newAssignment.setUser(user);
        newAssignment.setStatus("Pending Submission");

        // Save the assignment with the user set
        return assignmentRepository.save(newAssignment);
    }



}
