package com.jrl.employeetracker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

@Repository
public class EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	private static Employees list = new Employees();
	
	static {
		list.getEmployeeMap().put(1, new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com"));
        list.getEmployeeMap().put(2, new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeMap().put(3, new Employee(3, "David", "Kameron", "titanic@gmail.com"));
		
	}
	
	public EmployeeDAO() {
		
	}
	
	public Employees getAllEmployees() {
		return list;
	}
	
	public void addEmployee(Employee employee) {
		list.getEmployeeMap().put( employee.getEmployeeId(), employee);
	}
	
	public static Employee getEmployeeById(int id) {
		logger.debug("Employee id = " + id);
		if (!list.getEmployeeMap().containsKey(id)) {
			logger.debug("Employee not found - returning null");
			return null;
		} else {
			return list.getEmployeeById(id);			
		}
	}
	
}
