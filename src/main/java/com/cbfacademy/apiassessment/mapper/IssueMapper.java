package com.cbfacademy.apiassessment.mapper;

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

    @Mapping(source = "assignedTo", target = "assignedTo", qualifiedByName = "mapToEmployeeDTO")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "mapToEmployeeDTO")
    IssueDTO mapToIssueDTO(Issue issue);

    @Mapping(source = "assignedTo", target = "assignedTo", qualifiedByName = "mapToEmployeeDTO")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "mapToEmployeeDTO")
    List<IssueDTO> mapToIssueDTOList(List<Issue> issues);


    // Qualified by name method
    @Named("mapToEmployeeDTO")
    default List<EmployeeDTO> mapToEmployeeDTO(List<String> employeeList) {
        if (employeeList == null) {
            return null;
        }

        return employeeList.stream()
                .map((Function<String, EmployeeDTO>) EmployeeMapper.INSTANCE::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }
}
