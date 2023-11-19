package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.repository.IssueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.utils.IssueConverter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@EnableSwagger2
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
   }
}
