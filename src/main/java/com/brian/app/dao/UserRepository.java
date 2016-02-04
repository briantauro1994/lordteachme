package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brian.app.model.User;

@Repository
public class UserRepository {

	
	
	
	public void save(User user){
		
		em.persist(user);
	}
	
	
	
	
@PersistenceContext
private EntityManager em;



}
