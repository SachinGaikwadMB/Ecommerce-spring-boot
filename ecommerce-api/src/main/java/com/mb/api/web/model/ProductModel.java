package com.mb.api.web.model;

public class ProductModel
{
	private String name;

	private String category;
	
	private String modelNumber;
	
	private String brand;
	
	private double price;
	
	private String description;
	
	public ProductModel() { }

	public ProductModel(String name, String category, String modelNumber, String brand, double price, String description)
	{
		super();
		this.name = name;
		this.category = category;
		this.modelNumber = modelNumber;
		this.brand = brand;
		this.price = price;
		this.description = description;
	}

	public String getName()
	{
		return name;
	}

	public String getCategory()
	{
		return category;
	}

	public String getModelNumber()
	{
		return modelNumber;
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
	
}
