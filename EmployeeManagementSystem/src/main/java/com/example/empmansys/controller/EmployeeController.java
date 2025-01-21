package com.example.empmansys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.empmansys.models.Employee;
import com.example.empmansys.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//Display the list of elements
	//All the employee details is assigned to listEmployees and sent to index.html page
	//We write model to send the data to UI
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,model);
	}
	
	
	//Create model attribute to send the data to UI where "employee" is declared 
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	//We are saving the employee to the database 
	//We are using ModelAttribute to take the details from the UI
	//Here we are getting Object but if we want to take 1 detail we use @RequestParam
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee ) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
		
	}
	
	//We write a method to handle update button in index.html page
	//If we get id by parameter we use @PathVariable 
	//we add data to model & we pass this data to template
	//We will get employee form the service
	//And it returns to update_employee html page
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
		
	}
	
	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable ( value = "id" ) long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	
	//We have created a method handler which will handle pagination
	//We are passing PageNo as PathVariable
	//And we retrieved list of employees
	//And we are adding all to Model attribute and returning it to index.html page
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
		
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize); 
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		
		return "index";
	}

}





















