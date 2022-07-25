package com.mb.api.persistance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "cart")
@Entity
public class Cart
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", insertable = true, updatable = true)
	private Product product;
	//one product are in many users cart or many users has unique product
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable =  false)
	private User user ;
	
	private Integer quantity;
	
	public Cart() {}

	public Cart(Product product, User user, Integer quantity)
	{
		super();
		this.product = product;
		this.user = user;
		this.quantity = quantity;
	}

	public Integer getId()
	{
		return id;
	}

	public Product getProduct()
	{
		return product;
	}

	public User getUser()
	{
		return user;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

}
