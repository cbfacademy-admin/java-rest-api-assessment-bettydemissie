package com.cbfacademy.apiassessment.controller;

import java.util.ArrayList;
import java.util.List;

import com.cbfacademy.apiassessment.model.dto.IssueDTO;
import com.cbfacademy.apiassessment.service.EmployeeService;
import com.cbfacademy.apiassessment.service.IssueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.service.IssueService;

@RestController
@RequestMapping("/api/v1/issues")
//appcontroller
public class IssueController{
    private final IssueServiceImpl issueService;
    private final EmployeeService employeeService;

    @Autowired
    public IssueController(IssueServiceImpl issueService, EmployeeService employeeService) {
        this.issueService = issueService;
        this.employeeService = employeeService; //separate
    }

    @PostMapping("/issue")
    public Issue addIssue(@RequestBody Issue issue) {
        System.out.println(issue); //reove
        return issueService.addIssue(issue);

    }
    @GetMapping("/issue/all")
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }
    
    @GetMapping("/issue/{id}")
    public Issue fetchIssueDetails(@PathVariable String id) {
        return issueService.fetchIssueDetails(id);
    }

    @PutMapping("/issue")
    public Issue updateIssue(@RequestBody Issue issue) {
        return issueService.updateIssue(issue);
    }
}
