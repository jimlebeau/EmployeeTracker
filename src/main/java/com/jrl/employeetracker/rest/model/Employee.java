package com.jrl.employeetracker.rest.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;



@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer employeeId;
	
	@NotEmpty(message = "first name must not be empty")
	private String firstName;
	
	@NotEmpty(message = "last name must not be empty")
	private String lastName;
	
	@NotEmpty(message = "email must not be emmpty")
	@Email(message = "email should be a valid email address")
	private String email;
	
	public Employee() {
		
	}
	
	public Employee(Integer id, String firstName, String lastName, String email) {
		super();
		this.employeeId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer id) {
		this.employeeId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
