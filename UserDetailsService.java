package com.cinema.service;

import java.util.List;

import com.cinema.model.users;



public interface UserDetailsService {
	
	users findById(long id);
	
	users findByName(String name);
	
//	void saveUser(User user);
//	
//	void updateUser(User user);
//	
//	void deleteUserById(long id);
//
//	List<User> findAllUsers(); 
//	
//	void deleteAllUsers();
//	
//	public boolean isUserExist(User user);
	
}