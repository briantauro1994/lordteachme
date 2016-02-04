package com.brian.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brian.app.model.Group;


@Repository
public class GroupRepository {

	public void save(Group group){
		
		em.persist(group);
	}
	
	
	
	
@PersistenceContext
private EntityManager em;

}
