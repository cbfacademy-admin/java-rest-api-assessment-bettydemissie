package com.cbfacademy.apiassessment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cbfacademy.apiassessment.model.entities.Issue;

@Service
public interface IssueService {
    //crud functions are implemented in the repository
    //calls the repository to create, read, update and delete issues
    //logic from repository this is where checks are done to see if the data being given from user is valid for database

    public Issue addIssue(Issue issue);

    public List<Issue> getAllIssues();

    public Issue fetchIssueDetails(String id);

    public Issue updateIssue(Issue issue);

}
