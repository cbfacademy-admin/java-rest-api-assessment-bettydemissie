package com.cbfacademy.apiassessment;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cbfacademy.apiassessment.entities.Employee;
import com.cbfacademy.apiassessment.entities.Issue;
import com.cbfacademy.apiassessment.enums.Department;
import com.cbfacademy.apiassessment.enums.Status;

@   Service
public class IssueService {
    public static List<Issue> getissues() {
        Department department = Department.GROUP_FUNCTIONS;
        Employee assignedTo = new Employee(1, "John", "Doe", "johndoe@example.com", department);
        Employee createdBy = new Employee(2, "Alice", "Smith", "alicesmith@example.com", department);
        Issue issue1 = new Issue(1, "Bug in Application", "Application crashes on startup", Status.PENDING,
                assignedTo, createdBy);
        List<Issue> issueList = new ArrayList<>();
        issueList.add(issue1);
        return issueList;
    }

    public static Issue createIssue(@RequestBody Issue issue) {
        return issue;
    }

}
