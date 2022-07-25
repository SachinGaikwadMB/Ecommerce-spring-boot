package com.mb.api.persistance.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity(name = "products")
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	
	private String modelNumber;
	

	private String category;
	
	private String brand;
	
	private double price;
	
	private String description;
	
	@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;
	
	//constructors
	public Product() {}

	public Product(String name, String modelNumber, String category, String brand, double price, String description, List<Cart> carts)
	{
		super();
		this.name = name;
		this.modelNumber = modelNumber;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.carts = carts;
	}

	public Integer getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getModelNumber()
	{
		return modelNumber;
	}

	public String getCategory()
	{
		return category;
	}

	public String getBrand()
	{
		return brand;
	}

	public double getPrice()
	{
		return price;
	}

	public String getDescription()
	{
		return description;
	}

	public List<Cart> getCarts()
	{
		return carts;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setCarts(List<Cart> carts)
	{
		this.carts = carts;
	}

	
}
