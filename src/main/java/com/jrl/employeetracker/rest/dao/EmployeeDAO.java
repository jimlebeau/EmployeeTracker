package com.jrl.employeetracker.rest.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrl.employeetracker.rest.model.Employee;
import com.jrl.employeetracker.rest.model.EmployeeRowMapper;

@Transactional
@Repository
public class EmployeeDAO implements IEmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT EmployeeId, FirstName, LastName, Email FROM Employees";
		RowMapper<Employee> rowMapper = new EmployeeRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		String sql = "SELECT EmployeeId, FirstName, LastName, Email FROM Employees WHERE EmployeeId = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return employee;
	}
	
	@Override
	public void addEmployee(Employee employee) {
		String sql = "INSERT INTO Employees (FirstName, LastName, Email) values (?, ?, ?)";
		jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getEmail());
		
		sql = "SELECT EmployeeId FROM Employees WHERE LastName = ?";
		int employeeId = jdbcTemplate.queryForObject(sql,  Integer.class, employee.getLastName());
		
		employee.setEmployeeId(employeeId);
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		String sql = "UPDATE Employees SET FirstName=?, LastName=?, Email=? WHERE EmployeeId=?";
		jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmployeeId());

	}

	@Override
	public void deleteEmployee(int employeeId) {
		String sql = "DELETE FROM Employees WHERE EmployeeId=?";
		jdbcTemplate.update(sql, employeeId);		
	}

	@Override
	public boolean employeeExists(String lastName) {
		String sql = "SELECT count(*) FROM Employees WHERE LastName = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, lastName);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
