package com.cbfacademy.apiassessment.service;

import java.io.IOException;
import java.util.List;

import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.repository.IssueRepository;
import com.cbfacademy.apiassessment.utils.NotFoundException;
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
            // Log the error or handle it as appropriate for your application
            throw new RuntimeException("Error getting all issues.", e);
        }
    }

    public void addIssue(Issue issue) {
        try {
            issueRepository.addIssue(issue);
        } catch (Exception e) {
            throw new RuntimeException("Error adding issue.", e);
        }
    }

    public Issue fetchIssueDetails(Long issueId) {
        try {
            return issueRepository.fetchIssueDetails(issueId);
        } catch (NotFoundException e) {
            log.warn("Issue not found.", e);
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching issue details.", e);
        }
    }

    public void updateIssueByStatus(Long issueId, String status) {
        try {
            issueRepository.updateIssueByStatus(issueId, status);
        } catch (NotFoundException e) {
            log.warn("Issue not found or updated status is the same as the current status.", e);
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating issue status.", e);
        }
    }

    public void updateIssueByEmployee(Long issueId, Long employeeId) {
        try {
            issueRepository.updateIssueByEmployee(issueId, employeeId);
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

    public List<Issue> getIssuesByEmployeeId(Long employeeId) {
        try {
            return issueRepository.getIssuesByEmployeeId(employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting issues by employee ID.", e);
        }
    }
}
