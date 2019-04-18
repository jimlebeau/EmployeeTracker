package com.jrl.employeetracker.rest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getInt("EmployeeId"));
		employee.setFirstName(rs.getString("FirstName"));
		employee.setLastName(rs.getString("LastName"));
		employee.setEmail(rs.getString("Email"));
		return employee;
	}
}
