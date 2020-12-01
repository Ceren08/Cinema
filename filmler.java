package com.cinema.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;


@Entity
@Table(name="filmler")
@NamedQuery(name="filmler.findAll", query="SELECT d FROM filmler d")
public class filmler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "filmler_seq", sequenceName = "filmler_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "filmler_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;

	@ManyToOne
	@JoinColumn(name="salonid")
	private salonlar salonlar;

	@ManyToOne
	@JoinColumn(name="seansid")
	private seanslar seanslar;
	

	public filmler() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public salonlar getSalonlar() {
		return salonlar;
	}


	public void setSalonlar(salonlar salonlar) {
		this.salonlar = salonlar;
	}


	public seanslar getSeanslar() {
		return seanslar;
	}


	public void setSeanslar(seanslar seanslar) {
		this.seanslar = seanslar;
	}



	
}	
