package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
@Api(tags = "Issue API", description = "Endpoints for managing and tracking issues within the organization.")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @ApiOperation(value = "Add a new issue", notes = "Create a new issue with the provided details.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Issue created successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/add")
    public ResponseEntity<Void> addIssue(@RequestBody Issue issue) {
        try {
            issueService.addIssue(issue);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get all issues", notes = "Retrieve a list of all issues.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved issues"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAllIssues() {
        try {
            List<Issue> issues = issueService.getAllIssues();
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Fetch issue details", notes = "Retrieve details of a specific issue based on its ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved issue details"),
            @ApiResponse(code = 404, message = "Issue not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/fetch/{issueId}")
    public ResponseEntity<Issue> fetchIssueDetails(@PathVariable Long issueId) {
        try {
            Issue issue = issueService.fetchIssueDetails(issueId);
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update issues assigned to an employee", notes = "Update the assigned employee for a specific issue.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Issue updated successfully"),
            @ApiResponse(code = 404, message = "Issue or employee not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("/update/{issueId}")
    public ResponseEntity<Void> updateIssuesAssignedToEmployee(@PathVariable Long issueId,
                                                               @RequestParam Long employeeId) {
        try {
            issueService.updateIssuesAssignedToEmployee(issueId, employeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update issue status", notes = "Update the status of a specific issue.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Issue status updated successfully"),
            @ApiResponse(code = 404, message = "Issue not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("/update/status/{issueId}")
    public ResponseEntity<Void> updateIssueByStatus(@PathVariable Long issueId,
                                                    @RequestParam String status) {
        try {
            issueService.updateIssueByStatus(issueId, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete an issue", notes = "Delete a specific issue based on its ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Issue deleted successfully"),
            @ApiResponse(code = 404, message = "Issue not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/void/{issueId}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long issueId) {
        try {
            issueService.deleteIssue(issueId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get issues by status", notes = "Retrieve a list of issues based on their status.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved issues by status"),
            @ApiResponse(code = 404, message = "Issues not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Issue>> getIssuesByStatus(@PathVariable String status) {
        try {
            List<Issue> issues = issueService.getIssuesByStatus(Status.valueOf(status));
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get issues assigned to an employee", notes = "Retrieve a list of issues assigned to a specific employee.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved issues assigned to the employee"),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Issue>> getIssuesAssignedToEmployee(@PathVariable Long employeeId) {
        try {
            List<Issue> issues = issueService.getIssuesAssignedToEmployee(employeeId);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
