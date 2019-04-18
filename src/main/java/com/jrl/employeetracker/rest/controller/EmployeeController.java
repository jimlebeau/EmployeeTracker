package com.jrl.employeetracker.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.jrl.employeetracker.rest.exception.RecordNotFoundException;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.service.IEmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService service;
	
	@GetMapping(path  = "/", produces = "application/json")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = service.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		logger.debug("in mapping /employees/{" + id + "}");
		Employee employee = service.getEmployeeById(id);
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<Void> addEmployee (@Valid @RequestBody Employee employee, UriComponentsBuilder builder) {
		service.addEmployee(employee);
		boolean flag = service.addEmployee(employee);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("employees/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value = "/")
	public ResponseEntity<Employee> updateEmployee (@Valid @RequestBody Employee employee) {
		service.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id) {
		service.deleteEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
