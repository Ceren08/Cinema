package com.cinema.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.cinema.model.filmler;

import net.sf.jasperreports.engine.JRException;


public interface FilmDao {


	public List<filmler> filmlerListesi();
	void AddFilm(filmler film);
	public void filmlerSil(int id);
	String rapor(HttpServletResponse response) throws JRException, IOException, SQLException;
	String filtreliRapor(HttpServletResponse response, int id) throws JRException, IOException, SQLException;


}