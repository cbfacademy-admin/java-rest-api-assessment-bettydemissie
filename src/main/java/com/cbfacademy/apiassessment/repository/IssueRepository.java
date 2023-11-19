package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.exemptionhandling.AlreadyExistsExemption;
import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;
import com.cbfacademy.apiassessment.model.enums.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        try {
            return issueConverter.readJsonFile(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error reading issues from file.", e);
        }
    }
    public void addIssue(Issue issue) {
        try {
            List<Issue> issues = getAllIssues();
            //Check if there are issues in the issues list
            if (issues == null) {
                throw new IllegalStateException("Error reading existing issues from the file.");
            }
            // Check if the issueId already exists
            if (doesIssueExist(issue.getId())) {
                throw new AlreadyExistsExemption("Issue with ID " + issue.getId() + " already exists.");
            }
            issues.add(issue);
            saveIssue(issues, filePath);
            log.info("Issue added successfully.");

        } catch (AlreadyExistsExemption e) {
            throw e; // Re-throw AlreadyExistsExemption directly
        } catch (Exception e) {
            log.error("Error while adding issue.", e);
            throw new RuntimeException("Error while adding issue.", e);
        }
    }

    public Issue fetchIssueDetails(Long issueId) {
        try {
            if (!doesIssueExist(issueId)) {
                throw new NotFoundException("Issue not found");
            } else {
                List<Issue> issues = getAllIssues();
                return issues.stream()
                        .filter(issue -> issue.getId().equals(issueId))
                        .findAny()
                        .orElse(null);
            }
        } catch (IOException e) {
            log.error("Error fetching issue details.", e);
            throw new RuntimeException("Error fetching issue details.", e);
        }
    }

    public void updateIssueByStatus(Long issueId, String status) {
        try {
            List<Issue> issues = getAllIssues();

            boolean issueFound = false;

            for (Issue issue : issues) {
                if (issue.getId().equals(issueId)) {
                    if (!issue.getStatus().getStatus().equals(status)) {
                        // Convert string to Status class
                        Status newStatus = Status.valueOf(status);
                        issue.setStatus(newStatus);
                        saveIssue(issues, filePath);
                    }
                    issueFound = true;
                    break; // Assuming issueId is unique, exit loop after the first match
                }
            }

            if (!issueFound) {
                throw new NotFoundException("Issue not found");
            }

        } catch (Exception e) {
            log.error("Error updating issue status.", e);
            throw new RuntimeException("Error updating issue status.", e);
        }
    }

    //Change the employee assigned to an Issue
    public void updateIssuesAssignedToEmployee(Long issueId, Long employeeId) {
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
                issue.setAssignedTo(employeeRepository.getEmployeeById(employeeId));

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
            log.error("Error deleting issue.", e);
            throw new RuntimeException("Error deleting issue.", e);
        }
    }

    //Retrieve Issues based on Status, for example, retrieve all "COMPLETE" issues
    public List<Issue> getIssuesByStatus(Status status) {
        try {
            // Ensure the issues are sorted by status
            List<Issue> issues = getAllIssues();
            Collections.sort(issues, Comparator.comparing(Issue::getStatus));

            // Perform binary search
            int index = binarySearch(issues, status);

            if (index != -1) {
                // If the status is found, return the list of issues with that status
                List<Issue> issuesWithStatus = new ArrayList<>();
                for (int i = index; i >= 0 && issues.get(i).getStatus() == status; i--) {
                    issuesWithStatus.add(issues.get(i));
                }
                for (int i = index + 1; i < issues.size() && issues.get(i).getStatus() == status; i++) {
                    issuesWithStatus.add(issues.get(i));
                }
                return issuesWithStatus;
            } else {
                // If the status is not found
                throw new NotFoundException("No issue found with status: " + status);
            }
        } catch (Exception e) {
            log.error("Error while fetching issues by status", e);
            throw new NotFoundException("Error while fetching issues by status");
        }
    }

    //Retrieve all the issues that a given Employee has been assigned
    public List<Issue> getIssuesAssignedToEmployee(Long employeeId) {
        try {
            // Check if the employee exists
            if (!employeeRepository.checkEmployeeExist(employeeId)) {
                throw new NotFoundException("This employee does not exist");
            } else {
                List<Issue> issues = getAllIssues();

                List<Issue> issuesForEmployee = new ArrayList<>();

                for (Issue issue : issues) {
                    if (issue.getAssignedTo().getId().equals(employeeId)) {
                        issuesForEmployee.add(issue);
                    }
                }

                if (issuesForEmployee.isEmpty()) {
                    throw new NotFoundException("No assigned issues found for employee with ID: " + employeeId);
                } else {
                    return issuesForEmployee;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting Issues belonging to this employee");
        }
    }

    //Internal method to see if the issueId exists in the json
    public boolean doesIssueExist(Long issueId) throws IOException {
        List<Issue> issuesList = getAllIssues();
        return issuesList.stream().anyMatch(issue -> issue.getId().equals(issueId));
    }

    //Internal method to implement binary search algorithm
    private int binarySearch(List<Issue> issues, Status status) {
        int low = 0;
        int high = issues.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Status midStatus = issues.get(mid).getStatus();

            if (midStatus == status) {
                return mid; // Found the status
            } else if (midStatus.compareTo(status) < 0) {
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return -1; // Status not found
    }

    //save a single issue to the json file
    public void saveIssue(Issue issue, String filePath) {

        // Read existing issues from the file
        List<Issue> existingIssues = issueConverter.readJsonFile(filePath);

        // Check if the list is null (indicating an error reading the file)
        if (existingIssues == null) {
            // Handle the error, for example, throw an exception or log a message
            throw new NotFoundException("No issues from the file found.");
        }
        // Append the new or edited issue to the list
        else {
            existingIssues.add(issue);
            // Write the updated list of issues back to the file
            issueConverter.writeJsonFile(existingIssues, filePath);
        }
    }

    //method overload to save a list of issues to the json file
    public void saveIssue(List<Issue> issues, String filePath) {
        // Write the list of issues to the file
        issueConverter.writeJsonFile(issues, filePath);
    }
}