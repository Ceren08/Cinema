package com.cinema.dao;

import java.util.List;

import com.cinema.model.users;

public interface UserDetailsDao{
	users findUserByUsername(String username);

	void create(users user);

	List read();
	
	List<users> findAllUsers(); 

}
