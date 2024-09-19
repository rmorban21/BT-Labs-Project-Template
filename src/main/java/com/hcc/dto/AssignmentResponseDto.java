package com.hcc.dto;

import com.hcc.entities.Assignment;
import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

public class AssignmentResponseDto {
    private Long id;
    private String status;
    private int number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    private String username; // From User
    private String codeReviewer; // From User

    private AssignmentEnum[] assignmentEnums = AssignmentEnum.values();
    private AssignmentStatusEnum[] statusEnums = AssignmentStatusEnum.values();

    public AssignmentResponseDto(Assignment assignment) {
        this.id = assignment.getId();
        this.status = assignment.getStatus();
        this.number = assignment.getNumber();
        this.githubUrl = assignment.getGithubUrl();
        this.branch = assignment.getBranch();
        this.reviewVideoUrl = assignment.getReviewVideoUrl();
        this.username = assignment.getUser().getUsername();
        this.codeReviewer = assignment.getCodeReviewer().getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getCodeReviewer() {
        return codeReviewer;
    }

    public AssignmentEnum[] getAssignmentEnums() {
        return assignmentEnums;
    }

    public AssignmentStatusEnum[] getAssignmentStatusEnums() {
        return statusEnums;
    }
}
