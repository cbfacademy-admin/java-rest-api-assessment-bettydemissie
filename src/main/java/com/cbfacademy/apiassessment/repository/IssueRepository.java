package com.cbfacademy.apiassessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;

import java.util.List;

@Repository
public class IssueRepository {
    private String filePath = "src/main/resources/issues.json";

    @Autowired
    private IssueConverter issueConverter;

    public IssueRepository(@Value("${file.path}") String filePath) {
        this.filePath = filePath;
    }

    public List<Issue> getAllIssues() {
        return issueConverter.readJsonFile(filePath);
    }

    public Issue addIssue(Issue issue){
        // Read existing issues from the file
        List<Issue> issues = issueConverter.readJsonFile(filePath);

        // Check if the list is null (indicating an error reading the file)
        if (issues == null) {
            // Handle the error, for example, throw an exception or log a message
            throw new RuntimeException("Error reading existing issues from the file.");
        }

        // Add the new issue to the list
        issues.add(issue);

        // Write the updated list of issues back to the file
        issueConverter.writeJsonFile(issues, filePath);

        // Return the added issue
        return issue;
    }


}

