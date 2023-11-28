package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;

@Mapper
public interface AutoDepartmentMapper {

	AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);
	
	Department mapToDepartment(DepartmentDto departmentDto);
	
	DepartmentDto mapToDepartmentDto(Department department);
}
