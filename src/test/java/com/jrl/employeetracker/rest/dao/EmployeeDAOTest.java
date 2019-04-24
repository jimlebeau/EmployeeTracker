package com.jrl.employeetracker.rest.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.jrl.employeetracker.rest.model.Employee;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class EmployeeDAOTest {

	@Autowired
	private EmployeeDAO dao;
	

	private Employee emp1;
	private Employee emp2;
	
	@Before
	public void setUp() {
		
		emp1 = new Employee(1, "firstName1", "lastName1", "email1");
		emp2 = new Employee(2, "firstName2", "lastName2", "email2");
		
	}
	
	
	@Test
	public void constructorTest() throws Exception {
		dao = new EmployeeDAO();
		assertThat(dao, notNullValue());
	}

	@Test
	public void add_shouldCreateEmployee_AndGetById() {
		dao.addEmployee(emp1);
		
		Employee result = dao.getEmployeeById(emp1.getEmployeeId());
		assertThat(result.getFirstName(), equalTo(emp1.getFirstName()));
		
	}

	@Test
	public void getAllEmployees_shouldReturnEmployees() {
		dao.addEmployee(emp1);
		dao.addEmployee(emp2);
		
		List<Employee> employees = dao.getAllEmployees();
		assertThat(employees, hasSize(2));
		
	}

	@Ignore
	@Test
	public void updateEmployee_shouldUpdateEmployee() {
		
	}
	
	@Ignore
	@Test
	public void deleteEmployee_shouldDeleteEmployee() {
		
	}

}
