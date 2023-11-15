package com.cbfacademy.apiassessment.mapper;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.entities.Issue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.mapstruct.Named;

import com.cbfacademy.apiassessment.model.dto.EmployeeDTO;
import com.cbfacademy.apiassessment.model.dto.IssueDTO;

@Mapper
public interface IssueMapper {

    IssueMapper INSTANCE = Mappers.getMapper(IssueMapper.class);

    @Mapping(source = "assignedTo", target = "assignedTo", qualifiedByName = "map")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "map")
    IssueDTO mapToIssueDTO(Issue issue);

    List<IssueDTO> mapToIssueDTOList(List<Issue> issues);

    @Named("map")
    EmployeeDTO mapToEmployeeDTO(Employee employee);

    List<EmployeeDTO> mapToEmployeeDTOList(List<Employee> employees);

}



