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
	private final String GET_SQL = "SELECT EmployeeId, FirstName, LastName, Email FROM Employees";
	private final String GET_SQL_BY_ID = "SELECT EmployeeId, FirstName, LastName, Email FROM Employees WHERE EmployeeId = ?";
	private final String GET_SQL_BY_LAST_NAME = "SELECT EmployeeId FROM Employees WHERE LastName = ?";
	private final String ADD_SQL = "INSERT INTO Employees (FirstName, LastName, Email) values (?, ?, ?)";
	private final String UPDATE_SQL = "UPDATE Employees SET FirstName=?, LastName=?, Email=? WHERE EmployeeId=?";
	private final String DELETE_SQL = "DELETE FROM Employees WHERE EmployeeId=?";
	private final String EXISTS_SQL =  "SELECT count(*) FROM Employees WHERE LastName = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public List<Employee> getAllEmployees() {
		RowMapper<Employee> rowMapper = new EmployeeRowMapper();
		return this.jdbcTemplate.query(GET_SQL, rowMapper);
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(GET_SQL_BY_ID, rowMapper, id);
		return employee;
	}
	
	@Override
	public void addEmployee(Employee employee) {
		jdbcTemplate.update(ADD_SQL, employee.getFirstName(), employee.getLastName(), employee.getEmail());
		
		int employeeId = jdbcTemplate.queryForObject(GET_SQL_BY_LAST_NAME,  Integer.class, employee.getLastName());
		
		employee.setEmployeeId(employeeId);
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		jdbcTemplate.update(UPDATE_SQL, employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmployeeId());

	}

	@Override
	public void deleteEmployee(int employeeId) {
		jdbcTemplate.update(DELETE_SQL, employeeId);		
	}

	@Override
	public boolean employeeExists(String lastName) {
		int count = jdbcTemplate.queryForObject(EXISTS_SQL, Integer.class, lastName);
		if (count == 0) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
	
}
