package com.brian.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.brian.app.dao.UserRepository;
import com.brian.app.dto.UserDto;
import com.brian.app.model.User;
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Transactional
	public void saveUser(UserDto userDto)
	{
User user = new User(userDto.getCustomername(),userDto.getCustomerpassword());
        
        logger.info("Creating a new User " + user);
        
        userRepository.save(user);
	}
	private static final Logger logger = Logger.getLogger(UserService.class);
}
