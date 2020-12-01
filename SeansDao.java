package com.cinema.dao;
import java.util.List;

import com.cinema.model.seanslar;

public interface SeansDao {

	public List<seanslar> seanslarListesi();	
	void SeansEkle(seanslar seanslar);
	void UpdateSeans(seanslar id);
	void Deleteseanslar(int id);
	public seanslar getSeanslar(int id);

	
	

}