package com.cinema.dao;

import java.util.List;

import com.cinema.model.authorities;


public interface AuthoritiesDao{
	
	List findAuthorityByName(String username);

	List<authorities> read();

	void create(authorities authorities);

}
