package com.cbfacademy.apiassessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;

import java.util.List;

//@Repository
//public class IssueRepository {
//    private String filePath = "src/main/resources/issues.json";
//    private IssueConverter issueConverter;
//
//    public IssueRepository(@Value("${file.path}") String filePath) {
//        this.filePath = filePath;
//    }
//
//    public List<Issue> getAllIssues() {
//        return issueConverter.readJsonFile(filePath);
//    }
//
//
//}

@Repository
public class IssueRepository {
    private String filePath = "src/main/resources/issues.json";

    private IssueConverter issueConverter;

    public void setIssueConverter(IssueConverter issueConverter) {
        this.issueConverter = issueConverter;
    }

    public IssueRepository(IssueConverter issueConverter) {
        this.issueConverter = issueConverter;

    }

    public List<Issue> getAllIssues() {
        return issueConverter.readJsonFile(filePath);
    }
}

