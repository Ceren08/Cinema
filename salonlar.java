package com.cinema.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="salonlar")
@NamedQuery(name="salonlar.findAll", query="SELECT d FROM salonlar d")
public class salonlar implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "salonadi_seq", sequenceName = "salonadi_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "salonadi_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;

	@Column(name="adi")
	private String adi;
	
	@OneToMany(mappedBy="salonlar", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<filmler> filmler;
	

	public salonlar() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAdi() {
		return adi;
	}


	public void setAdi(String adi) {
		this.adi = adi;
	}


	public List<filmler> getFilmler() {
		return filmler;
	}


	public void setFilmler(List<filmler> filmler) {
		this.filmler = filmler;
	}
	

}