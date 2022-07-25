package com.mb.api.persistance.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	
	
	//Constructors
	public User() { }


	public User(Integer id, String email, String username, String password, Set<Role> roles)
	{
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	
	//Getters And Setters
	public Integer getId()
	{
		return id;
	}


	public String getEmail()
	{
		return email;
	}


	public String getUsername()
	{
		return username;
	}


	public String getPassword()
	{
		return password;
	}


	public Set<Role> getRoles()
	{
		return roles;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public void setEmail(String email)
	{
		this.email = email;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
	
	
}
