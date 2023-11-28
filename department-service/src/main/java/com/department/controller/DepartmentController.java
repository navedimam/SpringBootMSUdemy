package com.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentDto;
import com.department.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/depart")
@AllArgsConstructor
public class DepartmentController {

	private DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto departmentDtoReturn = departmentService.addDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(departmentDtoReturn, HttpStatus.CREATED);
	}

	
	@GetMapping("/bycode/{code}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("code") String code) {
		DepartmentDto department = departmentService.getDepartmentByCode(code);
		return ResponseEntity.ok(department);
	}

	// build rest api for get department by id
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long id) {
		DepartmentDto department = departmentService.getDepartmentById(id);
		return ResponseEntity.ok(department);
	}

	// build for get all deartment
	@GetMapping("/all")
	public ResponseEntity<List<DepartmentDto>> listOfDepartment() {
		List<DepartmentDto> list = departmentService.getAllDepartment();
		return ResponseEntity.ok().body(list);
	}

	// build update department
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDto> putUpdateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto) {
		departmentDto.setId(id);
		DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);

		return ResponseEntity.ok(updatedDepartment);
	}

	// delete department
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long id) {
		departmentService.deleteDepartmentById(id);

		return ResponseEntity.ok("Department deleted successfully");

	}

}
