package com.cinema.dao;

import java.util.List;

//import org.hibernate.Query;
//import javax.management.Query;
//import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;


import com.cinema.model.salonlar;

@EnableTransactionManagement
@Transactional
@Repository("SalonDao")
public class SalonDaoImpl implements SalonDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<salonlar> salonlarListesi() {
		List<salonlar> salonlar= sessionFactory.getCurrentSession().createQuery("From salonlar").list();
		if(salonlar.size()>0)
			return salonlar;
		else
			return null;
	}

	@Override
	public void Deletesalonlar(int id)
	{
		salonlar salon = (salonlar) sessionFactory.getCurrentSession().load(salonlar.class, id);
		if (null !=salon) 
		{
			this.sessionFactory.getCurrentSession().delete(salon);
		}
	}	
	@Override
	public void salonEkle1(salonlar salon) {
		sessionFactory.getCurrentSession().save(salon);
	}

	@Override
	public void Updatesalonlar(salonlar salon) {

		sessionFactory.getCurrentSession().update(salon);
	}
	@Override
	public salonlar getsalonlar(int salonid) {
		return (salonlar) sessionFactory.getCurrentSession().get(salonlar.class,salonid);
	}

	@Override
	public List read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List readById(String string, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
