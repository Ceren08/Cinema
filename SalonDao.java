package com.cinema.dao;

import java.util.List;
import com.cinema.model.salonlar;

public interface SalonDao {	
	
	public List<salonlar> salonlarListesi();
	public void salonEkle1(salonlar salon);
	void Deletesalonlar(int id);
	void Updatesalonlar(salonlar salon);
	public salonlar getsalonlar(int salonid);
//	
	public List read();
	public List readById(String string, int id);
	

	
	
}