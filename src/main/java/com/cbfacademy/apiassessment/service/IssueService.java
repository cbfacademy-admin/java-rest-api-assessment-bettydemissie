package com.cbfacademy.apiassessment.service;

import java.util.List;

import com.cbfacademy.apiassessment.exemptionhandling.AlreadyExistsExemption;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.repository.IssueRepository;
import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.model.entities.Issue;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(IssueService.class);

    public List<Issue> getAllIssues() {
        try {
            return issueRepository.getAllIssues();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all issues.", e);
        }
    }

    public void addIssue(Issue issue) {
        try {
            issueRepository.addIssue(issue);
        } catch (AlreadyExistsExemption e) {
            throw e; // Re-throw AlreadyExistsExemption directly
        } catch (Exception e) {
            throw new RuntimeException("Error adding issue.", e);
        }
    }

    public Issue fetchIssueDetails(Long issueId) {
        try {
            return issueRepository.fetchIssueDetails(issueId);
        } catch (NotFoundException e) {
            log.error("Issue not found.", e);
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching issue details.", e);
        }
    }

    public void updateIssueByStatus(Long issueId, String status) {
        try {
            issueRepository.updateIssueByStatus(issueId, status);
        } catch (NotFoundException e) {
            log.error("Issue not found or updated status is the same as the current status.", e);
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating issue status.", e);
        }
    }

    public void updateIssuesAssignedToEmployee(Long issueId, Long employeeId) {
        try {
            issueRepository.updateIssuesAssignedToEmployee(issueId, employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Error updating issue employee.", e);
        }
    }

    public void deleteIssue(Long issueId) {
        try {
            issueRepository.deleteIssue(issueId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting issue.", e);
        }
    }

    public List<Issue> getIssuesByStatus(Status status) {
        try {
            return issueRepository.getIssuesByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException("Error getting issues by status.", e);
        }
    }

    public List<Issue> getIssuesAssignedToEmployee(Long employeeId) {
        try {
            return issueRepository.getIssuesAssignedToEmployee(employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting issues by employee ID.", e);
        }
    }
}
