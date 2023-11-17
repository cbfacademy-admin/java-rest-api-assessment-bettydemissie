package com.cbfacademy.apiassessment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cbfacademy.apiassessment.model.entities.Issue;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface IssueService {
    //crud functions are implemented in the repository
    //calls the repository to create, read, update and delete issues
    //logic from repository this is where checks are done to see if the data being given from user is valid for database

    void addIssue(Issue issue);

    List<Issue> getAllIssues();

    Issue fetchIssueDetails(Long id);

    void updateIssueByStatus(Long issueId, String status);

    void deleteIssue(@RequestBody Long issueId);

    void updateIssueByEmployee(Long issueId, Long employeeId);

}
