package com.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.ApiResponseEmployee;
import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/emp")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employeeDtoReturn = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employeeDtoReturn, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseEmployee> getEmmployeeById(@PathVariable("id") Long id){
		ApiResponseEmployee employeeDto = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employeeDto);
	}

}
