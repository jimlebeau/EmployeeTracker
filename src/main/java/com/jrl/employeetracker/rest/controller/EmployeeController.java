package com.jrl.employeetracker.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jrl.employeetracker.rest.dao.EmployeeDAO;
import com.jrl.employeetracker.rest.exception.RecordNotFoundException;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@GetMapping(path  = "/", produces = "application/json")
	public Employees getEmployees() {
		return employeeDao.getAllEmployees();
	}
	
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		System.out.println("id = " + id);
		Employee employee = EmployeeDAO.getEmployeeById(id);
		
		if (employee == null) {
			throw new RecordNotFoundException("Invalid Employee id :" + id);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
//	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Object> addEmployee(
//	        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
//	        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") 
//	        String headerLocation,
//	        @RequestBody Employee employee) throws Exception {
//		
//		employeeDao.addEmployee(employee);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(employee.getId())
//				.toUri();
//				
//		return ResponseEntity.created(location).build();
//	}
	
	@PostMapping(value = "/employees")
	public ResponseEntity<Employee> addEmployee (@Valid @RequestBody Employee employee) {
		employeeDao.addEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
