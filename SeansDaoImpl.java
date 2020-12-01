package com.cinema.dao;

import java.util.List;

//import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;


import com.cinema.model.seanslar;

@EnableTransactionManagement
@Transactional
@Repository("seanslarDao")
public class SeansDaoImpl implements SeansDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<seanslar> seanslarListesi() {
		List<seanslar> seanslar=(List<seanslar>) sessionFactory.getCurrentSession().createQuery("From seanslar").list();
		if(seanslar.size()>0)
			return seanslar;
		else
			return null;
	}
	
	@Override
	public void Deleteseanslar(int id)
	{
		seanslar seanslar = (seanslar) sessionFactory.getCurrentSession().load(seanslar.class, id);
		if (null !=seanslar) 
		{
			this.sessionFactory.getCurrentSession().delete(seanslar);
		}
	}
	
	@Override
	public void SeansEkle(seanslar seanslar) {
		sessionFactory.getCurrentSession().save(seanslar);
	}

	@Override
	public void UpdateSeans(seanslar seanslar) {
		sessionFactory.getCurrentSession().update(seanslar);
	}

	@Override
	public seanslar getSeanslar(int id) {
		return (seanslar) sessionFactory.getCurrentSession().get(seanslar.class,id);
	}

}
