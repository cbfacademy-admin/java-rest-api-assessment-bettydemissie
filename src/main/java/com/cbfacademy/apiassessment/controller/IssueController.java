package com.cbfacademy.apiassessment.controller;

import java.util.List;

import com.cbfacademy.apiassessment.service.EmployeeService;
import com.cbfacademy.apiassessment.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cbfacademy.apiassessment.model.entities.Issue;

@RestController
@RequestMapping("/api/v1/issues")
//appcontroller
public class IssueController{
    private final IssueService issueService;
    private final EmployeeService employeeService;

    @Autowired
    public IssueController(IssueService issueService, EmployeeService employeeService) {
        this.issueService = issueService;
        this.employeeService = employeeService; //separate
    }

    @PostMapping("/add")
    public void addIssue(@RequestBody Issue issue) {
        issueService.addIssue(issue);
    }

    @GetMapping("/all")
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }
    
    @GetMapping("/fetch/{issueId}")
    public Issue fetchIssueDetails(@PathVariable Long issueId) {
        return issueService.fetchIssueDetails(issueId);
    }

    @PutMapping("/update/{issueId}")
    public void updateIssueByEmployee(@PathVariable Long issueId,
                                      @RequestParam Long employeeId) {
        issueService.updateIssueByEmployee(issueId, employeeId);
    }

    @PutMapping("/update/status/{issueId}")
    public void updateIssueByStatus(@PathVariable Long issueId,
                                    @RequestParam String status) {
        issueService.updateIssueByStatus(issueId, status);
    }

    @PutMapping("/void/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
    }
}
