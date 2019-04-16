package com.jrl.employeetracker.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrl.employeetracker.rest.exception.RecordNotFoundException;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;
import com.jrl.employeetracker.rest.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService = new EmployeeService();
	
	@GetMapping(path  = "/", produces = "application/json")
	public Employees getEmployees() {
		return employeeService.getEmployees();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		logger.debug("in mapping /employees/{" + id + "}");
		Employee employee = employeeService.getEmployeeById(id);
		
		if (employee == null) {
			throw new RecordNotFoundException("Invalid Employee id :" + id);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<Employee> addEmployee (@Valid @RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
