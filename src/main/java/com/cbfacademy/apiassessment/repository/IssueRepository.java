package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;
import com.cbfacademy.apiassessment.model.enums.Status;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class IssueRepository {
    private String filePath = "src/main/resources/issues.json";

    @Autowired
    private IssueConverter issueConverter;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger log = LoggerFactory.getLogger(IssueRepository.class);

    public IssueRepository(@Value("${file.path}") String filePath) {
        this.filePath = filePath;
    }

    public List<Issue> getAllIssues() {
        return issueConverter.readJsonFile(filePath);
    }

    //TODO
    //should return void
    public void addIssue(Issue issue) {
        // Read existing issues from the file
        List<Issue> issues = getAllIssues();

        // Check if the list is null (indicating an error reading the file)
        if (issues == null) {
            // Handle the error, for example, throw an exception or log a message
            throw new RuntimeException("Error reading existing issues from the file.");
        }

        // Add the new issue to the list
        issues.add(issue);

        // Write the updated list of issues back to the file
        saveIssue(issues, filePath);
        //can return a message using log.info
    }

    public boolean doesIssueExist(Long issueId) throws IOException {
        List<Issue> issuesList = getAllIssues();
        return issuesList.stream().anyMatch(issue -> issue.getId().equals(issueId));
    }

    public Issue fetchIssueDetails(Long issueId) {
        try {
            if (!doesIssueExist(issueId)) {
                return null;
            } else {
                List<Issue> issues = getAllIssues();
                return issues.stream()
                        .filter(issue -> issue.getId().equals(issueId))
                        .findAny()
                        .orElse(null);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
            return null;
        }
    }

    public void updateIssueByStatus(Long issueId, String status) throws IOException {
        List<Issue> issues = getAllIssues();
        //convert string to Status class
        for (Issue issue : issues) {
            if (issue.getId().equals(issueId) && issue.getStatus().getStatus() != status) {
                //map to converted status from above
                Status newStatus = Status.valueOf(status);
                issue.setStatus(newStatus);
                saveIssue(issue, filePath);
            }
        }
        // Handle the case where the issue with given ID is not found
    }

    public void updateIssueByEmployee(Long issueId, Long employeeId) throws IOException {
        List<Issue> issues = getAllIssues();
        // Check if the employeeId exists in the JSON file
        //why are the methods from the employee repo not working?
        if (!(employeeRepository.checkEmployeeExist(employeeId))) {
            log.info("Employee with ID " + employeeId + " does not exist.");
        } else {
            // Check if the issueId exists and update the assignedTo field
            for (Issue issue : issues) {
                if (issue.getId().equals(issueId)) {
                    // Update the assignedTo field with the new employeeId
                    issue.setAssignedTo(employeeRepository.getEmployeeByID(employeeId));

                    // Write the updated list of issues back to the file
                    saveIssue(issues, filePath);
                }
            }

        }
        // Handle the case where the issueId or the employeeId is not found
    }

//    public void updateIssueByEmployee(Long issueId, Long employeeId) throws IOException {
//        //check that employee id exists in json file
//        //check that issue exists in json

    //i redid this method above

    //check if employee and issue exists
    //if exists and assi

//        if (!doesIssueExist(issueId) && issue.getAssignedTo().getId() != employeeId) {
//                //map employee to new assigned
//                issue.setAssignedTo(employeeId);
//
//                //create a save method for this in the repo saveIssue??yes
//                issueConverter.writeJsonFile(issues, filePath);
//
//            }
//        }
//        // Handle the case where the issue with given ID is not found
//    }


    public void deleteIssue(Long issueId) throws IOException {
        List<Issue> issues = getAllIssues();

        issues.removeIf(issue -> issue.getId().equals(issueId));

        saveIssue(issues, filePath);
    }

    //TODO
    //save one issue at a time
    //this currently only saves a list
    //or consider retrieving issues entity as list
    //append new or edited issue to list
    //and then save the list of issues
    //for methods that need to save issues

    public void saveIssue(Issue issue, String filePath) {

        // Read existing issues from the file
        List<Issue> existingIssues = issueConverter.readJsonFile(filePath);

        // Check if the list is null (indicating an error reading the file)
        if (existingIssues == null) {
            // Handle the error, for example, throw an exception or log a message
            log.info("Error reading existing issues from the file.");
        }
        // Append the new or edited issue to the list
        existingIssues.add(issue);

        // Write the updated list of issues back to the file
        issueConverter.writeJsonFile(existingIssues, filePath);
    }

    public void saveIssue(List<Issue> issues, String filePath) {
        // Write the list of issues to the file
        issueConverter.writeJsonFile(issues, filePath);

    }

}