package com.example.empmansys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.empmansys.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
