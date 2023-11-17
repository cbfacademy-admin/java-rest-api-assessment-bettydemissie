package com.cbfacademy.apiassessment.service;

import java.io.IOException;
import java.util.List;

import com.cbfacademy.apiassessment.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    @Override
    public void addIssue(Issue issue) { issueRepository.addIssue(issue);
    }

    @Override
    public Issue fetchIssueDetails(Long id) {
        try {
            return issueRepository.fetchIssueDetails(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateIssueByStatus(Long issueId, String status) {
        try {
            issueRepository.updateIssueByStatus(issueId, status);
        } catch (IOException e) {
            // Handle the exception according to your application's requirements
            e.printStackTrace();
        }
    }

    @Override
    public void updateIssueByEmployee(Long issueId, Long employeeId) {
        try {
            issueRepository.updateIssueByEmployee(issueId, employeeId);
        } catch (IOException e) {
            // Handle the exception according to your application's requirements
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIssue(Long issueId) {
        try {
            issueRepository.deleteIssue(issueId);
        } catch (IOException e) {
            // Handle the exception according to your application's requirements
            e.printStackTrace();
        }
    }
}
