package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brian.app.model.Department;
import com.brian.app.model.Employee;

@Repository
public class EmployeeRepository {
	@PersistenceContext
	EntityManager em;

	public void save(Employee employee)
	{
		em.persist(employee);
	}
}
