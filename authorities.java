package com.cinema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class authorities implements Serializable {
  
  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "authorities_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "authorities_seq", sequenceName = "authorities_seq", initialValue = 1, allocationSize = 1)
  private int id;
	
  @Column(name = "authority")
  private String authority;
  
  @Column(name = "username")
  private String username;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getAuthority() {
	return authority;
}

public void setAuthority(String authority) {
	this.authority = authority;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

  
//bi-directional many-to-one association to User
//	@ManyToOne( fetch=FetchType.EAGER)
//	@JoinColumn(name="username" , insertable=false, updatable=false)
//	public users user;


	

	

}