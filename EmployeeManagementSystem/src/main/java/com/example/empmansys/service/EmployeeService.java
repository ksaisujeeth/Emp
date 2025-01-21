package com.example.empmansys.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.empmansys.models.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployeeById (long id);
	
	Page<Employee> findPaginated(int pageNo, int pageSize);
	
	
}
