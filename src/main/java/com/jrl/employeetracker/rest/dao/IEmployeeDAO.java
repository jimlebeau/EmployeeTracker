package com.jrl.employeetracker.rest.dao;

import java.util.List;

import com.jrl.employeetracker.rest.model.Employee;

public interface IEmployeeDAO {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int EmployeeId);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(int EmployeeId);
	boolean employeeExists(String lastName);

}
