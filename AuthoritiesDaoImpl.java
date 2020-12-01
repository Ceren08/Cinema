package com.cinema.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.dao.AuthoritiesDao;
import com.cinema.model.authorities;

@Repository
public class AuthoritiesDaoImpl implements AuthoritiesDao  {
	
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public List findAuthorityByName(String username) {
		
		String hql = "select authority from authorities  where username = :username";
		List result = sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter("username", username)
		.list();
		
		return result;
	
	}



	@Override
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<authorities> read() {
		
		 return sessionFactory.getCurrentSession().createQuery("FROM " +  authorities.class)
	                .getResultList();
    }



	@Override
	public void create(authorities authorities) {
		
	}

	 
}
