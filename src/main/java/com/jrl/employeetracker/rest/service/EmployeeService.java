package com.jrl.employeetracker.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrl.employeetracker.rest.dao.EmployeeDAO;
import com.jrl.employeetracker.rest.dao.IEmployeeDAO;
import com.jrl.employeetracker.rest.model.Employee;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployeeDAO dao;

	public EmployeeService(EmployeeDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Employee obj = dao.getEmployeeById(id);
		return obj;
	}
	
	@Override
	public synchronized boolean addEmployee(Employee employee) {
		if (dao.employeeExists(employee.getLastName())) {
			return false;
		} else {
			dao.addEmployee(employee);
			return true;
		}
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		dao.updateEmployee(employee);
		
	}

	@Override
	public void deleteEmployee(int id) {
		dao.deleteEmployee(id);		
	}
	
}
