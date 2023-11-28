package com.department.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.exception.DepartmentCodeAlreadyPresentException;
import com.department.exception.ResourceNotFoundException;
import com.department.mapper.AutoDepartmentMapper;
import com.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository departmentRepository;
	
	private ModelMapper modelMapper;
	
	private static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	
	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
//		Department department = new Department();
//		department.setDepartmentCode(departmentDto.getDepartmentCode());
//		department.setDepartmentDescription(departmentDto.getDepartmentDescription());
//		department.setDepartmentName(departmentDto.getDepartmentName());
		
//		Department department = modelMapper.map(departmentDto, Department.class);
		if(departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode())!=null) {
			throw new DepartmentCodeAlreadyPresentException("Dedpartment with code "+departmentDto.getDepartmentCode()+" is already present");
		}
		
		if(departmentRepository.findByDepartmentName(departmentDto.getDepartmentCode())!=null) {
			throw new DepartmentCodeAlreadyPresentException("Dedpartment with name "+departmentDto.getDepartmentCode()+" is already present");
		}
		
		Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
		
		Department savedDepartment =  departmentRepository.save(department);
		
//		DepartmentDto departmentDtoReturn = new DepartmentDto(
//				savedDepartment.getId(),
//				savedDepartment.getDepartmentName(),
//				savedDepartment.getDepartmentDescription(),
//				savedDepartment.getDepartmentCode()
//				);
		
//		DepartmentDto departmentDtoReturn = modelMapper.map(savedDepartment, DepartmentDto.class);
		
		DepartmentDto departmentDtoReturn =AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
		return departmentDtoReturn;
	}
	
	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		logger.trace("get drapartment by code()");
		Department department = departmentRepository.findByDepartmentCode(departmentCode);
		if(department==null) {
			throw new ResourceNotFoundException("Department", "Department id", departmentCode);
					
		}
//		DepartmentDto departmentDto= new DepartmentDto(
//				department.getId(),
//				department.getDepartmentName(),
//				department.getDepartmentDescription(),
//				department.getDepartmentCode()
//				);
//		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		
		DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
		
		return departmentDto;
	}


	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				()-> new ResourceNotFoundException("Department", "Department id", departmentId)
				);
//		DepartmentDto departmentDto = new DepartmentDto(
//				department.getId(),
//				department.getDepartmentName(),
//				department.getDepartmentDescription(),
//				department.getDepartmentCode()
//				); 
		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<Department> list = departmentRepository.findAll();
		
		
//		List<DepartmentDto> dtoList =list.stream().map((department)->{
//			return new DepartmentDto(
//					department.getId(),
//					department.getDepartmentName(),
//					department.getDepartmentDescription(),
//					department.getDepartmentCode()
//					);
//		}).collect(Collectors.toList());
		
		List<DepartmentDto> dtoList =list.stream().map((department)->{
			return modelMapper.map(department, DepartmentDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
		Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow(
				()-> new ResourceNotFoundException("Department", "Department id", departmentDto.getId())
				);
		
		department.setDepartmentCode(departmentDto.getDepartmentCode());
		department.setDepartmentDescription(departmentDto.getDepartmentDescription());
		department.setDepartmentName(departmentDto.getDepartmentName());
		
		Department savedDepartment =  departmentRepository.save(department);
				
//		DepartmentDto departmentDtoReturn = new DepartmentDto(
//				department.getId(),
//				department.getDepartmentName(),
//				department.getDepartmentDescription(),
//				department.getDepartmentCode()
//				); 
		
		DepartmentDto departmentDtoReturn = modelMapper.map(savedDepartment, DepartmentDto.class);
		return departmentDtoReturn;
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.findById(departmentId).orElseThrow(
				()-> new ResourceNotFoundException("Department", "Department id", departmentId)
				);
		departmentRepository.deleteById(departmentId);
		
	}

	
}
