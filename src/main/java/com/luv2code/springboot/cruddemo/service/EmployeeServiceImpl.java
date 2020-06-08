package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("employeeDAOJpaImpl")//if multiple object is trying to inject then use qualifier to use which want to inject
	private EmployeeDAO theEmployeeDao;
	@Override
	@Transactional
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return theEmployeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		return theEmployeeDao.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		theEmployeeDao.save(theEmployee);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		theEmployeeDao.deleteById(theId);

	}

}
