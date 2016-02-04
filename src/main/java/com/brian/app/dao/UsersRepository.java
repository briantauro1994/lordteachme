package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import com.brian.app.model.Users;

@Repository
public class UsersRepository {

	public void save(Users user){
		
		em.persist(user);
	}
	
	
	
	
@PersistenceContext
private EntityManager em;

}
