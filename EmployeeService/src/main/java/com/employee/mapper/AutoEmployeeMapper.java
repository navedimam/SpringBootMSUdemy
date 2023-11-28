package com.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

@Mapper
public interface AutoEmployeeMapper {

	AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
	
	Employee maptoEmployee(EmployeeDto employeeDto);
	
	EmployeeDto maptoEmployeeDto(Employee employee);
}
