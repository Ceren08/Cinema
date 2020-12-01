package com.cinema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="seanslar")
@NamedQuery(name="seanslar.findAll", query="SELECT d FROM seanslar d")
public class seanslar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seanslar_seq", sequenceName = "seanslar_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "seanslar_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;

	@Column(name="tarih")
	private  String tarih;

	@OneToMany(mappedBy="seanslar", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<filmler> filmler;
	

	public seanslar() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTarih() {
		return tarih;
	}


	public void setTarih(String tarih) {
		this.tarih = tarih;
	}


	public List<filmler> getFilmler() {
		return filmler;
	}


	public void setFilmler(List<filmler> filmler) {
		this.filmler = filmler;
	}

	
	
}