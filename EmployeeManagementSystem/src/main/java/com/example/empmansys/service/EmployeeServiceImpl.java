package com.example.empmansys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.empmansys.models.Employee;
import com.example.empmansys.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository ;
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}


	//Create a optional with Employee return type
	//If there are employees present than it will save it in employee obj
	//If not throw an exception
	@Override
	public Employee getEmployeeById(long id) {

		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		}else {
			throw new RuntimeException("Employee with this id is not fount :: "+id);
		}
		return employee;
	}

	
	@Override
	public void deleteEmployeeById(long id) {

		this.employeeRepository.deleteById(id);
	}

	//To use paging we use Page Interface and write PageNo & PageSize
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.employeeRepository.findAll(pageable);
	}

}















