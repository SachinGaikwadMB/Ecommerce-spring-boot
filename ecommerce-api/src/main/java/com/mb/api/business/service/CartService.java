package com.mb.api.business.service;

import java.util.List;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.AddToCartModel;

public interface CartService
{
	String addTocart(AddToCartModel addToCartModel);
	List<Product> getAllCartItems();
	String removeItemFromCart(Integer id);
	 
	
}
