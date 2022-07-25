package com.mb.api.business.service;

import java.util.Map;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.ProductModel;

public interface ProductService
{
	String saveProduct(ProductModel productModel);
	Map<String, Object> getAllProducts();
	Product getProductById(Integer id);
	String updateProduct(Integer id, ProductModel productModel);
	String deleteProduct(Integer id);
	
	
}
