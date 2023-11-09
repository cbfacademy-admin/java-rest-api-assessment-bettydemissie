package com.cbfacademy.apiassessment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.entities.Issue;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<Issue> getissues() {
        return issueService.getissues();

    }

    @PostMapping
    public Issue createIssue(Issue issue) {
        return issueService.createIssue(issue);
    }

    

}
