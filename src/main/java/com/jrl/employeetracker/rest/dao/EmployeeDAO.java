package com.jrl.employeetracker.rest.dao;

import org.springframework.stereotype.Repository;

import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

@Repository
public class EmployeeDAO {

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
		System.out.println("getEmployeeById = " + id);
		System.out.println(list.getEmployeeMap().containsKey(id));
		if (list.getEmployeeMap().containsKey(id)) {
			return null;
		} else {
			return list.getEmployeeById(id);
			
		}
	}
	
}
