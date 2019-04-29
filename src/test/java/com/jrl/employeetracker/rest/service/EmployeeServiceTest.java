package com.jrl.employeetracker.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.jrl.employeetracker.rest.dao.EmployeeDAO;
import com.jrl.employeetracker.rest.dao.IEmployeeDAO;
import com.jrl.employeetracker.rest.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeDAO dao;
	
//	@InjectMocks
	private EmployeeService service;
	
	@Before
	public void setup() {
		dao = Mockito.mock(EmployeeDAO.class);
		service = new EmployeeService(dao);
		
	}
	
	@Test
	public void getAllEmployeesSucess() throws Exception {
		
		Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
		Employee employee2 = new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com");
		Employee employee3 = new Employee(3, "David", "Kameron", "titanic@gmail.com");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		
		Mockito.when(dao.getAllEmployees()).thenReturn(employeeList);
		
		assertThat(service.getAllEmployees(), hasSize(3));
		
	}
	
	@Test
	public void addEmployee_shouldReturnTrue() throws Exception {
		Employee employee4 = new Employee(4, "Lokesh", "Gupta", "lokesh@gmail.com");
		Mockito.doNothing().when(dao).addEmployee(employee4);
		Mockito.when(dao.employeeExists(any(String.class))).thenReturn(Boolean.FALSE);
		Boolean added = service.addEmployee(employee4);
		assertThat(added, equalTo(Boolean.TRUE));
		
	}
	
	@Test
	public void addEmployee_shouldReturnFalse() {
		Employee employee4 = new Employee(4, "Lokesh", "Gupta", "lokesh@gmail.com");
		Mockito.when(dao.employeeExists(any(String.class))).thenReturn(Boolean.TRUE);
		Boolean added = service.addEmployee(employee4);
		assertThat(added, equalTo(Boolean.FALSE));
		
	}
	
	@Test
	public void getEmployeeByIdSuccess() throws Exception {
		Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");		
		Mockito.when(dao.getEmployeeById(1)).thenReturn(employee1);		
		Employee employee = service.getEmployeeById(1);
				
		assertThat(employee.getEmployeeId(), equalTo(1));
	}
	
	@Test
	public void updateEmployee_shouldReturnFalse() {
		Employee employee = new Employee(null, "Lokesh", "Gupta", "lokesh@gmail.com");
		assertThat(service.updateEmployee(employee), equalTo(Boolean.FALSE));
	}
	
	@Test 
	public void updateEmployee_shouldReturnTrue() {
		Employee employee1 = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
		Mockito.doNothing().when(dao).updateEmployee(employee1);
		assertThat(service.updateEmployee(employee1), equalTo(Boolean.TRUE));
		
	}
}
