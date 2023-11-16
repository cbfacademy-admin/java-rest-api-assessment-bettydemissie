package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.repository.IssueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.utils.IssueConverter;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@EnableSwagger2
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
		String filePath = "src/main/resources/issues.json";

		IssueConverter issueConverter = new IssueConverter();

//		// Read the JSON file and get a list of Issue objects
//		List<Issue> issues = issueConverter.readJsonFile(filePath);
//
//		// Print the deserialized issues
//		if (issues != null) {
//			for (Issue issue : issues) {
//				System.out.println(issue);
//			}
//		}

//        IssueRepository issueRepository = new IssueRepository(filePath);
//
//        List<Issue> issuesList = issueRepository.getAllIssues();
//
//        for (Issue issue : issuesList) {
//            System.out.println(issue);
//        }
//
//
//	// Create instances of Employee
//	Employee assignedEmployee = new Employee(5, "John", "Doe", "john@example.com", Department.GROUP_FUNCTIONS);
//	Employee createdEmployee = new Employee(6, "Jane", "Doe", "jane@example.com", Department.ASSET_MANAGEMENT);
//
//	// Create instances of Issue with assigned and created employees
//	List<Issue> issuesToWrite = new ArrayList<>();
//	issuesToWrite.add(new Issue(1, "IT Issue", "Computer Failure", Status.PENDING, assignedEmployee, createdEmployee));
//
//	var writeIssue = issueConverter.writeJsonFile(issuesToWrite, filePath);
//
   }
}
