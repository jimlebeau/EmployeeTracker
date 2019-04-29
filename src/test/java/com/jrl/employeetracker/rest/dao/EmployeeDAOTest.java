package com.jrl.employeetracker.rest.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
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

	@Test
	public void updateEmployee_shouldUpdateEmployee() {
		String newEmail = "newEmail.com";
		dao.addEmployee(emp1);
		emp1.setEmail(newEmail);
		dao.updateEmployee(emp1);
		assertThat(emp1.getEmail(), equalTo(newEmail));
		
	}
	
	@Test
	public void deleteEmployee_shouldDeleteEmployee() {
		dao.addEmployee(emp1);
		dao.addEmployee(emp2);
		dao.deleteEmployee(emp1.getEmployeeId());
		assertThat(dao.employeeExists(emp1.getLastName()), equalTo(Boolean.FALSE));
		
	}
	
	@Test
	public void employeeExists_shouldReturnTrue() {
		dao.addEmployee(emp1);
		assertThat(dao.employeeExists(emp1.getLastName()), equalTo(Boolean.TRUE));
		
	}

	@Test
	public void employeeExist_shouldReturnFalse() {
		dao.addEmployee(emp1);
		assertThat(dao.employeeExists(emp2.getLastName()), equalTo(Boolean.FALSE));
	}
}
