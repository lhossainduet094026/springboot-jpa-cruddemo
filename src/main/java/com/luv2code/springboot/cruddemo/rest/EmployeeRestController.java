package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	//quick and dirty :inject EmployeeDAO
	@Autowired
	private EmployeeService employeeService;
	//constructor injection
	/*@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDao)
	{
		employeeDao=theEmployeeDao;
	}
	*/
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee=employeeService.findById(employeeId);
		if(theEmployee==null)
		{
			throw new RuntimeException("Employee Id not found-"+employeeId);
		}
		
		return theEmployee;
	}
	@PostMapping("/employee")
	public void saveEmployee(@RequestBody Employee theEmployee) {
		//send json data and set id =0 to force a save instead of update
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		
	}
	//add mapping put for update existing data
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/employees")
		public Employee updateEmployee(@RequestBody Employee theEmployee) {
			
			employeeService.save(theEmployee);
			
			return theEmployee;
		}
		// add mapping for DELETE /employees/{employeeId} - delete employee
		
		@DeleteMapping("/employees/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {
			
			Employee tempEmployee = employeeService.findById(employeeId);
			
			// throw exception if null
			
			if (tempEmployee == null) {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}
			
			employeeService.deleteById(employeeId);
			
			return "Deleted employee id - " + employeeId;
		}
}
