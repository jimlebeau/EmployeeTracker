package com.jrl.employeetracker.rest.model;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeTest {
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void shouldContructEmployee() throws Exception {
		Employee employee = new Employee(1, "first", "last", "email");
		assertEquals(employee.getEmployeeId().toString(), "1");
		assertEquals(employee.getFirstName(), "first");
		assertEquals(employee.getLastName(), "last");
		assertEquals(employee.getEmail(), "email");		
	}
	
	@Test
	public void shouldDeserializeEmployee() throws Exception {
		String employeeString = "{\"employeeId\":1,\"firstName\":\"first\",\"lastName\":\"last\",\"email\":\"email\"}";
		Employee employee = new Employee(1, "first", "last", "email");
		String actual = mapper.writeValueAsString(employee);
		assertEquals(employeeString, actual);
	}
	
	@Test
	public void shouldSerializeEmployee() throws Exception {
		Employee employee = new Employee(1, "first", "last", "email");
		InputStream input = new FileInputStream("/Users/jameslebeau/TutorialProject/EmployeeTracker/src/test/java/com/jrl/employeetracker/resource/employee.json");
		Employee inputEmployee = mapper.readValue(input, Employee.class);
		assertThat(inputEmployee, samePropertyValuesAs(employee));
	}
}
