package com.cbfacademy.apiassessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;

import java.util.List;

@Repository
public class IssueRepository {
    //after reading and deserialised the json file then set up the repository
    private final String filepath = "src/main/resources/issues.json";
    private final IssueConverter issueConverter;

  @Autowired
  public IssueRepository(IssueConverter issueConverter) {
        this.issueConverter = issueConverter;
    }

    public List<Issue> getAllIssues() {
        return issueConverter.readJsonFile(filepath);
    }

    
}
