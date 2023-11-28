package com.department.service;

import java.util.List;

import com.department.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto addDepartment(DepartmentDto departmentDto);
	
	DepartmentDto getDepartmentByCode (String departmentCode);
	
	DepartmentDto getDepartmentById (Long departmentId);
	
	List<DepartmentDto> getAllDepartment();
	
	DepartmentDto updateDepartment(DepartmentDto departmentDto);
	
	void deleteDepartmentById(Long departmentId);
	
	
}
