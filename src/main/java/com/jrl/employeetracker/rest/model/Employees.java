package com.jrl.employeetracker.rest.model;

import java.util.HashMap;
import java.util.Map;

public class Employees {
	
	private Map<Integer, Employee> employeeMap = new HashMap<>();
	
	public Map<Integer, Employee> getEmployeeMap() {
		if (employeeMap.isEmpty())
			employeeMap = new HashMap<>();
		return employeeMap;
	}
	
	public void setEmployeeList(Map<Integer, Employee> employeeMap) {
		this.employeeMap = employeeMap;
	}

	public Employee getEmployeeById(int id) {
		if (employeeMap.containsKey(id)) {
			return employeeMap.get(id);
		} else {
			return null;
		}
	}
}
