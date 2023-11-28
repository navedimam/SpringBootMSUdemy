package com.employee.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.dto.ApiResponseEmployee;
import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.dto.OrganizationDto;
import com.employee.entity.Employee;
import com.employee.exception.EmailAlreadyPresentException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

//	private RestTemplate restTemplate;
	private WebClient webClient;
//	private FeignApiClient feignApiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//		Employee employee = new Employee();
//		employee.setEmail(employeeDto.getEmail());
//		employee.setFirstName(employeeDto.getFirstName());
//		employee.setLastName(employeeDto.getLastName());

		Optional<Employee> checkEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

		if (checkEmployee.isPresent()) {
			throw new EmailAlreadyPresentException(employeeDto.getEmail());
		}

		Employee employee = AutoEmployeeMapper.MAPPER.maptoEmployee(employeeDto);

		Employee savedEmployee = employeeRepository.save(employee);
//		
//		EmployeeDto employeeDtoReturn = new EmployeeDto(
//					savedEmployee.getId(),
//					savedEmployee.getFirstName(),
//					savedEmployee.getLastName(),
//					savedEmployee.getEmail()
//				);

		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(savedEmployee);
		return employeeDtoReturn;
	}

//	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
	@Override
	public ApiResponseEmployee getEmployeeById(Long employeeId) {
		logger.trace("get employee by code()");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee id", employeeId));
//		EmployeeDto employeeDtoReturn = new EmployeeDto(
//				employee.getId(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail()
//			);
		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);

//		ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/depart/bycode/"+employeeDtoReturn.getDepartmentCode(), DepartmentDto.class);
//		
//		DepartmentDto departmentDto = response.getBody();
		System.out.println(employeeDtoReturn.getDepartmentCode() + employeeDtoReturn.getOrganizationCode());
		DepartmentDto departmentDto = webClient.get()
				.uri("http://localhost:8080/api/depart/bycode/" + employeeDtoReturn.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();
		
		OrganizationDto organizationDto = webClient.get()
				.uri("http://localhost:8083/api/organizations/" + employeeDtoReturn.getOrganizationCode()).retrieve()
				.bodyToMono(OrganizationDto.class).block();

//		DepartmentDto departmentDto = feignApiClient.getDepartmentById(employeeDtoReturn.getDepartmentCode());

		return new ApiResponseEmployee(employeeDtoReturn, departmentDto, organizationDto);
	}

	public ApiResponseEmployee getDefaultDepartmentResponse(Long employeeId, Exception ex) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee id", employeeId));

		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);

		//ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/depart/bycode/"+employeeDtoReturn.getDepartmentCode(), DepartmentDto.class);
		//
		//DepartmentDto departmentDto = response.getBody();

//		DepartmentDto departmentDto = webClient.get()
//				.uri("http://localhost:8080/api/depart/bycode/" + employeeDtoReturn.getDepartmentCode()).retrieve()
//				.bodyToMono(DepartmentDto.class).block();
		
		DepartmentDto departmentDto = new DepartmentDto(12L, "defaultDepartmentName", "Default Description", "DD01");
		OrganizationDto organizationDto = new OrganizationDto(12L, "defaultOrgName", "Default Description", "DD01",LocalDateTime.now());
		
		//DepartmentDto departmentDto = feignApiClient.getDepartmentById(employeeDtoReturn.getDepartmentCode());
		return new ApiResponseEmployee(employeeDtoReturn, departmentDto,organizationDto);
	}
}
