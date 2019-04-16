package com.jrl.employeetracker.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Employees {
	
	private List<Employee> employees = new ArrayList<>();
	

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public List<Employee> getEmployees() {
		if (employees.isEmpty()) {
			employees = new ArrayList<>();
		}
		return employees;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
}
