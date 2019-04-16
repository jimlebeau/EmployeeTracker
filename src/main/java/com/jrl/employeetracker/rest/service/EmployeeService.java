package com.jrl.employeetracker.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrl.employeetracker.rest.dao.EmployeeDAO;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

@Service
public class EmployeeService {
	
	private EmployeeDAO dao;

	public EmployeeService(EmployeeDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void initializeEmployees() {
		Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
		Employee employee2 = new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com");
		Employee employee3 = new Employee(3, "David", "Kameron", "titanic@gmail.com");

		dao.addEmployee(employee1);
		dao.addEmployee(employee2);
		dao.addEmployee(employee3);		
	}
	
	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}
	
	public void addEmployee(Employee employee) {
		dao.addEmployee(employee);
	}
	
	public Employee getEmployeeById(int id) {
		return dao.getEmployeeById(id);
	}
	
	public Employees getEmployees() {
		return dao.getEmployees();
	}
	
}
