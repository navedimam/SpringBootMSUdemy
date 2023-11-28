package com.net.javaguides.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.net.javaguides.bean.Student;


@RestController
@RequestMapping("/api") // base url for the controller
public class StudentController {
	
	
	@GetMapping("/student")
	public ResponseEntity<Student> student() {
		Student s =  new Student(12, "name", "last");
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("custom", "1");
		map.add("custom1", "3");
		return new ResponseEntity<Student>(s,map,HttpStatus.OK);
		
//		return ResponseEntity.ok()
//					.header("custom-header", "value")
//					.body(s);
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> listOfstudent() {
		List<Student> list =Arrays.asList(new Student(12, "name", "last"), new Student(14, "name2", "last2"));
		
		return ResponseEntity.ok().body(list);
	}

	// @pathvariable
	@GetMapping("/student/{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentByPathVariable(@PathVariable("id") int id, 
			@PathVariable("first-name") String firstname,
			@PathVariable("last-name") String lastName) {
		
		Student s = new Student(id, firstname, lastName);
		return ResponseEntity.ok(s);
	}

	// @pathvariable
	@GetMapping("/student/query")
	public ResponseEntity<Student> studentByRequestparam(@RequestParam("id") int id, 
			@RequestParam(name = "firstName",defaultValue = "default") String firstname,
			@RequestParam("lastName") String lastName) {
		
		Student s = new Student(id, firstname, lastName);
		return ResponseEntity.ok(s);
	}
	
	//@postmapping and @requestbody
	@PostMapping("/student")
//	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Student> postStudent(@RequestBody Student student) {
		System.out.println(student.toString());
		student.setId((int)(Math.random()*100));
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	//@putmapping and @requestbody
	@PutMapping("/student/{id}/update")
	public ResponseEntity<Student> putUpdateStudent(@RequestBody Student student,@PathVariable("id") int sId) {
		System.out.println(student.toString());
		
		return ResponseEntity.ok(student);
	}
	
	//@deletemapping 
	@DeleteMapping("/student/{id}/delete")
	public ResponseEntity<String> dleteStudent(@PathVariable("id") int studentId) {
		System.out.println(studentId);
		
		return ResponseEntity.ok("student deleted successfully" + studentId);
	}
	
}
