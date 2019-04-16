package com.jrl.employeetracker.rest.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

@Repository
public class EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	private Employees employees;
	
	public EmployeeDAO() {
		employees = new Employees();
	}
	
	public Employees getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> emmployeeList) {
		employees.setEmployees(emmployeeList);
	}

	public List<Employee> getAllEmployees() {
		return employees.getEmployees();
	}
	
	public void addEmployee(Employee employee) {
		List<Employee> employeeList = employees.getEmployees();
		if (!employeeList.contains(employee)) {
			employees.addEmployee(employee);
		}
	}
	
	public Employee getEmployeeById(int id) {
		List<Employee> employeeList = employees.getEmployees();
		Employee returnEmployee = null;
		for(Employee employee :employeeList) {
			if (employee.getEmployeeId() == id)
				returnEmployee = employee;
		}
		return returnEmployee;
	}
	
	
}
