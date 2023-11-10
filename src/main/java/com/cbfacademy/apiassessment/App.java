package com.cbfacademy.apiassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.utils.IssueConverter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
		String filePath = "src/main/resources/issues.json";

		IssueConverter issueConverter = new IssueConverter();

		// Read the JSON file and get a list of Issue objects
		List<Issue> issues = issueConverter.readJsonFile(filePath);

		// Print the deserialized issues
		if (issues != null) {
			for (Issue issue : issues) {
				System.out.println(issue);
			}
		}
    }
}
