package com.brian.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.app.dao.GroupRepository;
import com.brian.app.dao.UsersRepository;
import com.brian.app.model.Group;
import com.brian.app.model.Users;

@Service
public class TestMAnytoOneService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	GroupRepository groupRepository;

	@Transactional
	public void save()
	{
		 Group groupAdmin = new Group("Administrator Group");
	        Group groupGuest = new Group("Guest Group");
	         
	        Users user1 = new Users("Tom", "tomcat", "tom@codejava.net");
	        Users user2 = new Users("Mary", "mary", "mary@codejava.net");
	         
	        groupAdmin.addUser(user1);
	        groupAdmin.addUser(user2);
	         
	        groupGuest.addUser(user1);
	         
	        user1.addGroup(groupAdmin);
	        user2.addGroup(groupAdmin);
	        user1.addGroup(groupGuest);
	         usersRepository.save(user1);
	         
	         usersRepository.save(user2);
	        
	         
	}
}
