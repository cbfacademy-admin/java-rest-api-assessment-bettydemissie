package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.mapper.IssueMapper;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.model.dto.EmployeeDTO;
import com.cbfacademy.apiassessment.model.dto.IssueDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mapstruct.factory.Mappers;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

    @TestInstance(Lifecycle.PER_CLASS)
    public class TestSuite {

        private final IssueMapper issueMapper = Mappers.getMapper(IssueMapper.class);

        @Test
        public void testMapToIssueDTO() {
            // Create a sample Issue object
            Issue issue = new Issue(1L, "Sample Issue", "Description", Status.PENDING,
                    new Employee(123456789, "john", "life", "john.doe@example.com", Department.WEALTH_MANAGEMENT),
                    new Employee(12345678, "john", "death", "john.doe@example.com", Department.ASSET_MANAGEMENT));

            // Map the Issue to IssueDTO
            IssueDTO issueDTO = issueMapper.mapToIssueDTO(issue);

            // Assertions
            assertNotNull(issueDTO);
            assertEquals(issue.getId(), issueDTO.getId());
            assertEquals(issue.getTitle(), issueDTO.getTitle());
            assertEquals(issue.getDescription(), issueDTO.getDescription());
            assertEquals(issue.getStatus().getStatus(), issueDTO.getStatus().getStatus());
            assertEquals(issue.getAssignedTo(), issueDTO.getAssignedTo());
            assertEquals(issue.getCreatedBy(), issueDTO.getCreatedBy());
        }

        @Test
        public void testMapToIssueDTOList() {
            // Create a list of sample Issue objects
            List<Issue> issues = Arrays.asList(
                    new Issue(1L, "Issue1", "Description1", Status.IN_PROGRESS,
                            new Employee(123456789, "john", "life", "john.doe@example.com", Department.WEALTH_MANAGEMENT),
                            new Employee(123456789, "jn", "le", "john.doe@ede.com", Department.WEALTH_MANAGEMENT)),
                    new Issue(2L, "Issue2", "Description2", Status.IN_PROGRESS,
                            new Employee(12345678, "john", "death", "john.doe@example.com", Department.ASSET_MANAGEMENT),
                            new Employee(123456789, "dn", "ld", "john.doe@example.co", Department.ASSET_MANAGEMENT))
            );


            // Map the list of Issues to a list of IssueDTOs
            List<IssueDTO> issueDTOList = issueMapper.mapToIssueDTOList(issues);

            // Assertions
            assertNotNull(issueDTOList);
            assertEquals(issues.size(), issueDTOList.size());

            // Add more specific assertions based on your mapping logic
        }

        // Add more test methods as needed
    }


