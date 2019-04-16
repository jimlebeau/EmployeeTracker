package com.jrl.employeetracker.rest.model;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;


import org.junit.Test;

public class EmployeesTest {
	
	private List<Employee> employeeList = new ArrayList<>();
	private Employees employees = new Employees();
	private Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
	private Employee employee2 = new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com");
	private Employee employee3 = new Employee(3, "David", "Kameron", "titanic@gmail.com");

	@Test
	public void setEmployeesTest() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		employees.setEmployees(employeeList);
		assertThat(employees, notNullValue());
	}
	
	@Test
	public void addEmployeeSuccess() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		employees.setEmployees(employeeList);
		Employee employee = new Employee(3, "Joe", "Employee", "joe@yahoo.com");
		
		employees.addEmployee(employee);
		List<Employee> newEmployeeList = employees.getEmployees();
		assertThat(newEmployeeList, hasSize(3));
	}
	
	@Test
	public void getEmployeesSuccess() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		employees.setEmployees(employeeList);
		List<Employee> newEmployeeList = employees.getEmployees();
		assertThat(newEmployeeList, hasSize(2));
	}
}
