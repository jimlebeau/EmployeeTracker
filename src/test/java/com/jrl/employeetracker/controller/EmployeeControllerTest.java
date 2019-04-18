package com.jrl.employeetracker.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.jrl.employeetracker.rest.controller.EmployeeController;
import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.service.EmployeeService;
import com.jrl.employeetracker.rest.service.IEmployeeService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	private static Employee employee = new Employee(1, "Lokesh", "Gupta", "lokesh@gmail.com");
	private static List<Employee> employees = new ArrayList<>();
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IEmployeeService service;
	
			
	@Ignore
	@Test
	public void testGetEmployeeListSuccess() throws Exception {
		
		employees.add(employee);
		
		Mockito.when(service.getAllEmployees()).thenReturn(employees);
		
		mvc.perform( get("/employees/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Ignore
	@Test 
	public void testGetEmployeeByIdSuccess() throws Exception {
		int id = 1;
		Mockito.when(service.getEmployeeById(id)).thenReturn(employee);
		mvc.perform( get("/employees/" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
}
