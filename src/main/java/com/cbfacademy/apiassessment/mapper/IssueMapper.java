package com.cbfacademy.apiassessment.mapper;

import com.cbfacademy.apiassessment.model.entities.Issue;

@Mapper
public interface IssueMapper {

    IssueMapper INSTANCE = Mappers.getMapper(IssueMapper.class);

    @Mapping(source = "assignedTo", target = "assignedTo")
    @Mapping(source = "createdBy", target = "createdBy")
    Issue mapToIssue(IssueDTO issueDTO);

    @Mapping(source = "assignedTo", target = "assignedTo")
    @Mapping(source = "createdBy", target = "createdBy")
    IssueDTO mapToIssueDTO(Issue issue);

}

