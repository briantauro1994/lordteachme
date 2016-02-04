package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brian.app.model.Category;

@Repository
public class CategoryRepository {
	@PersistenceContext
	EntityManager em;

	public void save(Category category)
	{
		em.persist(category);
	}
}
