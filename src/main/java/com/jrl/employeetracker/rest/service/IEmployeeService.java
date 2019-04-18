package com.jrl.employeetracker.rest.service;

import java.util.List;

import com.jrl.employeetracker.rest.model.Employee;

public interface IEmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	boolean addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(int id);
}
