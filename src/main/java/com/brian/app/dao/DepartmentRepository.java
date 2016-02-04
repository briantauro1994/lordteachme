package com.brian.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.brian.app.model.Department;


@Repository
public class DepartmentRepository {
@PersistenceContext
EntityManager em;

public void save(Department department)
{
	em.persist(department);
}
public List<Department> getdep()
{
	
	TypedQuery<Department> query=em.createNamedQuery("Department.findAll",Department.class);
	
	
	return  query.getResultList();
}
}
