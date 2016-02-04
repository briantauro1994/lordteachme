package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
