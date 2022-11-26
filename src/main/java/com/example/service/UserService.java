package com.example.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class.getSimpleName());  
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUser(User obj) {
		userRepository.save(obj);
	}
	
	public User getUser(long userId) {
		logger.log(Level.INFO,"get user by id :{0} ",userId);
		return userRepository.findById(userId).get();
	}
	
	public boolean deleteUser(long userId) {
		userRepository.deleteById(userId);
		return true;
	}
	
	public List<User> getUsers() {
		logger.log(Level.INFO,"****** Get all users ***** ");
		return userRepository.findAll();
	}
}
