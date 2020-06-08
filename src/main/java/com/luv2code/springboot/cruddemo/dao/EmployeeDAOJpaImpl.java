package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    @Autowired
    private EntityManager theEntityManager;
	@Override
	public List<Employee> findAll() {
		//create a query
		Query theQuery = theEntityManager.createQuery("from Employee");
		//execute query
		List<Employee> theEmployees= theQuery.getResultList();
		//return list of employee
		return theEmployees;
	}

	@Override
	public Employee findById(int theId) {
		//get the specific employee by id 
		Employee theEmployee = theEntityManager.find(Employee.class, theId);
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//save of update the employee
		Employee employee = theEntityManager.merge(theEmployee);
		//update with id from db........ 
        employee.setId(theEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {
		//create a delete query
		Query theQuery = theEntityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId",theId);
		//execute query
        theQuery.executeUpdate();
	}

}
