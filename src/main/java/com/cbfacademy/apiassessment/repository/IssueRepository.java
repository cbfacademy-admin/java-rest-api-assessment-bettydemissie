package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.utils.NotFoundException;
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

    public void addIssue(Issue issue) {
        try {
            // Read existing issues from the file
            List<Issue> issues = getAllIssues();
            // Check if the list is null
            if (issues == null) {
                // Handle the error
                throw new RuntimeException("Error reading existing issues from the file.");
            }
            // Add the new issue to the list
            issues.add(issue);
            // Write the updated list of issues back to the file
            saveIssue(issues, filePath);
            log.info("Issue added successfully.");
        } catch (Exception e) {
            // Log the exception
            log.error("Error adding issue.", e);
        }
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

//    public void updateIssueByEmployee(Long issueId, Long employeeId) throws IOException {
//        List<Issue> issues = getAllIssues();
//        // Check if the employeeId exists in the JSON file
//        if (!(employeeRepository.checkEmployeeExist(employeeId))) {
//            log.info("Employee with ID " + employeeId + " does not exist.");
//        } else {
//            // Check if the issueId exists and update the assignedTo field
//            for (Issue issue : issues) {
//                if (issue.getId().equals(issueId)) {
//                    // Update the assignedTo field with the new employeeId
//                    issue.setAssignedTo(employeeRepository.getEmployeeByID(employeeId));
//
//                    // Write the updated list of issues back to the file
//                    saveIssue(issues, filePath);
//                }
//            }
//        }
//        // Handle the case where the issueId or the employeeId is not found
//    }
    public void updateIssueByEmployee(Long issueId, Long employeeId) throws IOException {
        List<Issue> issues = getAllIssues();

        // Check if the employeeId exists in the JSON file
        if (!employeeRepository.checkEmployeeExist(employeeId)) {
            log.info("Employee with ID " + employeeId + " does not exist.");
            throw new NotFoundException("Employee with ID " + employeeId + " not found.");
        }

        // Check if the issueId exists and update the assignedTo field
        boolean issueFound = false;
        for (Issue issue : issues) {
            if (issue.getId().equals(issueId)) {
                // Update the assignedTo field with the new employeeId
                issue.setAssignedTo(employeeRepository.getEmployeeByID(employeeId));

                // Write the updated list of issues back to the file
                saveIssue(issues, filePath);

                issueFound = true;
                break; // Assuming issueId is unique, exit loop after the first match
            }
        }

        // Handle the case where the issueId is not found
        if (!issueFound) {
            log.info("Issue with ID " + issueId + " not found.");
            throw new NotFoundException("Issue with ID " + issueId + " not found.");
        }
    }

    public void deleteIssue(Long issueId) {
        try {
            List<Issue> issues = getAllIssues();

            // Check if the issueId exists before attempting to remove
            boolean issueExists = issues.removeIf(issue -> issue.getId().equals(issueId));

            if (!issueExists) {
                // Handle the case where the issue with the given ID is not found
                throw new NotFoundException("Issue not found with ID: " + issueId);
            }

            saveIssue(issues, filePath);
        } catch (Exception e) {
            // Log the exception or handle it based on your application's requirements
            e.printStackTrace();
            throw new RuntimeException("Error deleting issue.", e);
        }
    }

    public void saveIssue(Issue issue, String filePath) {

        // Read existing issues from the file
        List<Issue> existingIssues = issueConverter.readJsonFile(filePath);

        // Check if the list is null (indicating an error reading the file)
        if (existingIssues == null) {
            // Handle the error, for example, throw an exception or log a message
            log.info("Error reading existing issues from the file.");
        }
        // Append the new or edited issue to the list
        else {
            existingIssues.add(issue);
            // Write the updated list of issues back to the file
            issueConverter.writeJsonFile(existingIssues, filePath);
        }
    }

    public void saveIssue(List<Issue> issues, String filePath) {
        // Write the list of issues to the file
        issueConverter.writeJsonFile(issues, filePath);

    }

}