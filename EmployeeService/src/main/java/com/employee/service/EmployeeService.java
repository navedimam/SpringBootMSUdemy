package com.employee.service;

import com.employee.dto.ApiResponseEmployee;
import com.employee.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	ApiResponseEmployee getEmployeeById(Long employeeId);
}
