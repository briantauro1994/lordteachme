package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import com.brian.app.model.Product;

@Repository
public class ProductRepository {
	@PersistenceContext
	EntityManager em;

	public void save(Product product)
	{
		em.persist(product);
	}
}
