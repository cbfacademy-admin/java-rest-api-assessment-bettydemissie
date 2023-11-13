package com.cbfacademy.apiassessment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;

@Service
public class IssueServiceImpl implements IssueService {

    IssueConverter issueConverter = new IssueConverter();
    String filePath = "src/main/resources/issues.json";
    @Override
    public Issue addIssue(Issue issue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addIssue'");
    }

    @Override
    public List<Issue> fetchAllIssues() {
        List<Issue> issues = issueConverter.readJsonFile(filePath);
        return issues;
    }

    @Override
    public Issue fetchIssueDetails(String id) {
        try {
            // Assuming you have filePath and issueConverter defined somewhere
            List<Issue> issues = issueConverter.readJsonFile(filePath);

            // Use parallelStream() to filter issues based on ID
            Issue issue = issues.parallelStream()
                    .filter(iss -> String.valueOf(iss.getId()).equals(id))
                    .findAny()
                    .orElse(null);

            return issue;
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
            return null;
        }
    }
    
    @Override
    public Issue updateIssue(Issue updatedIssue) {
        List<Issue> issues = issueConverter.readJsonFile(filePath);

        // Find the issue with the matching ID
        for (Issue existingIssue : issues) {
            if (existingIssue.getId() == updatedIssue.getId()) {
                // Update the status of the existing issue
                existingIssue.setStatus(updatedIssue.getStatus());

                // Write the updated list back to the JSON file
                if (issueConverter.writeJsonFile(issues, "issues.json")) {
                    // Return the updated issue
                    return existingIssue;
                } else {
                    // Handle the case where writing to the file fails
                    throw new RuntimeException("Failed to update issue status in the JSON file.");
                }
            }
        }

        // If no matching issue is found, return null or throw an exception, depending
        // on your requirements
        return null;
    }

}
