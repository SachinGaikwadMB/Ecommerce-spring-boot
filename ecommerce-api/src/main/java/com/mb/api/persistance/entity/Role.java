package com.mb.api.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mb.api.enums.ERole;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private ERole roleName;
	
	//Contsructors
	public Role() {	}

	public Role(ERole roleName) {
		super();
		this.roleName = roleName;
	}
	
	
	//getters and setters
	public Integer getId()
	{
		return id;
	}

	public ERole getRole()
	{
		return roleName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setRole(ERole roleName)
	{
		this.roleName = roleName;
	}
	
	
}
