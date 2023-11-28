package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByDepartmentCode(String departmentCode);

	Department findByDepartmentName(String departmentCode);

	
}
