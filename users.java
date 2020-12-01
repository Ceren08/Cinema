package com.cinema.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class users implements Serializable{
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password", nullable = true)
  private String password;

  @Column(name = "enabled", nullable = true)
  private boolean enabled;


	//bi-directional many-to-one association to Authority
//	@OneToMany(cascade = CascadeType.ALL, mappedBy="user", fetch=FetchType.EAGER)
//	public List<authorities> authorities;
  

	
	public users() {
	
	}
	
	
	
	public users(String username, String password, boolean enabled, List<authorities> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		//this.authorities = authorities;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}



}