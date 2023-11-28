package com.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.dto.DepartmentDto;


//@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface FeignApiClient {

	@GetMapping("/api/depart/bycode/{code}")
	public DepartmentDto getDepartmentById(@PathVariable("code") String code);
	
	
}
