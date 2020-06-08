package com.luv2code.springboot.cruddemo.dao;

import java.util.List;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entitymanager
	@Autowired
	private EntityManager theEntityManager;
	//set up constructor injection 
	
	/*@Autowired
     public EmployeeDAOHibernateImpl(EntityManager entityManager) {
    	 
		theEntityManager = entityManager;
	}
	*/
	@Override
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session currentSession = theEntityManager.unwrap(Session.class);
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
		//execute query and get the resul list
		List<Employee> theEmployee= theQuery.getResultList();
		//return the result
		return theEmployee;
	}

	@Override
	public Employee findById(int theId) {
		//get the current hibernate session
		Session currentSession = theEntityManager.unwrap(Session.class);
		
		//get the employee
		Employee theEmployee = currentSession.get(Employee.class,theId);
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get the current hibernate session
		Session currentSession = theEntityManager.unwrap(Session.class);
		//save the employee
		currentSession.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		//get the current hibernate session
		Session currentSession = theEntityManager.unwrap(Session.class);
		//delete object using primary key
		Query theQuery = currentSession.createQuery("delete from Employee where Id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}
