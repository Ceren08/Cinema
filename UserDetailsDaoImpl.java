package com.cinema.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cinema.dao.UserDetailsDao;
import com.cinema.model.users;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

 

@Autowired
  private SessionFactory sessionFactory;
private List<users> users;

  @Override
  public users findUserByUsername(String username) {
    return (users)sessionFactory.getCurrentSession().get(users.class, username);
  }

@Override
public void create(users user) {
}

public List<users> findAllUsers() {
	return users;
}

@Override
public List read() {
	// TODO Auto-generated method stub
	return null;
}
}