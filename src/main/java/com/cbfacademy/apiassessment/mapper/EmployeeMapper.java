package com.cbfacademy.apiassessment.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


import com.cbfacademy.apiassessment.model.dto.EmployeeDTO;
import com.cbfacademy.apiassessment.model.entities.Employee;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    // Mapping method for Employee to EmployeeDTO
    EmployeeDTO mapToEmployeeDTO(Employee employee);
}
