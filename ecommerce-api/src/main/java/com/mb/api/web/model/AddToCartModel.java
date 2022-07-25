package com.mb.api.web.model;

public class AddToCartModel
{
	private Integer userId;
	private Integer productId;
	private Integer quantity;
	
	
	public AddToCartModel() {}


	public AddToCartModel(Integer userId, Integer productId, Integer quantity)
	{
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}


	public Integer getUserId()
	{
		return userId;
	}


	public Integer getProductId()
	{
		return productId;
	}


	public Integer getQuantity()
	{
		return quantity;
	}
	
	
}
