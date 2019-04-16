package com.jrl.employeetracker.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;

import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;

public class EmployeeDAOTest {

	private List<Employee> employeeList = new ArrayList<>();
	private Employees employees = new Employees();
	EmployeeDAO dao;
	private Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
	private Employee employee2 = new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com");
	private Employee employee3 = new Employee(3, "David", "Kameron", "titanic@gmail.com");

	@Test
	public void constructorTest() throws Exception {
		dao = new EmployeeDAO();
		assertThat(dao, notNullValue());
	}

	@Test
	public void addEmployeeSuccess() throws Exception {
		dao = new EmployeeDAO();
		dao.addEmployee(employee1);
		employeeList = dao.getAllEmployees();
		assertThat(employeeList, hasSize(1));
	}

	@Test
	public void setGetEmployeesSucess() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		EmployeeDAO dao = new EmployeeDAO();
		dao.setEmployees(employeeList);
		assertThat(dao.getEmployees(), notNullValue());		
	}
	
	@Test
	public void getAllEmployeesSuccess() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		EmployeeDAO dao = new EmployeeDAO();
		dao.setEmployees(employeeList);
		assertThat(dao.getAllEmployees(), hasSize(3));
		
	}
	@Test
	public void getEmployeeByIdSuccess() throws Exception {
		employeeList.add(employee1);
		employeeList.add(employee2);
		dao = new EmployeeDAO();
		dao.setEmployees(employeeList);
		Employee employee = dao.getEmployeeById(1);
		
		assertThat(employee.getEmployeeId(), equalTo(employee1.getEmployeeId()));
	}
}
