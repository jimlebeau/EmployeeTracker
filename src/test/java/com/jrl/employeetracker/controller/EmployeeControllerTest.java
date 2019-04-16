package com.jrl.employeetracker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jrl.employeetracker.rest.controller.EmployeeController;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.Employees;
import com.jrl.employeetracker.rest.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	private static Employees employees = new Employees();
	private static Employee employee = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
	
	
	@Autowired
	private MockMvc mvc;
	
	
	@MockBean 
	private EmployeeService service;
	
	@Test
	public void testGetEmployeeListSuccess() throws Exception {
		
		Mockito.when(service.getEmployees()).thenReturn(employees);
		
		mvc.perform( get("/employees/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test 
	public void testGetEmployeeByIdSuccess() throws Exception {
		int id = 1;
		Mockito.when(service.getEmployeeById(id)).thenReturn(employee);
		mvc.perform( get("/employees/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
}
