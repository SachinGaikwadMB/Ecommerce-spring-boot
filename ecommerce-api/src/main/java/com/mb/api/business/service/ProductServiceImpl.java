package com.mb.api.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.api.business.exception.CustomException;
import com.mb.api.enums.ErrorCode;
import com.mb.api.persistance.entity.Product;
import com.mb.api.persistance.repository.ProductRepository;
import com.mb.api.web.model.ProductModel;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String saveProduct(ProductModel productModel)
	{
		Product product = mapper.map(productModel, Product.class);

		if (productRepository.existsBymodelNumber(productModel.getModelNumber()))
		{
			throw new CustomException("product already exist", ErrorCode.PRODUCT_ALREADY_EXISTS);
		}

		try
		{
			productRepository.save(product);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return "product saved from service layer";
	}

	@Override
	public Map<String, Object> getAllProducts()
	{
		Map<String, Object> data = new HashMap<>();
		List<Product> productList = null;
		try
		{
			productList = productRepository.findAll();
		}
		catch (Exception ex)
		{
			throw new CustomException("Unable to fetch data", ErrorCode.INTERNAL_SERVER_ERROR);
		}

		data.put("message", "Product data fetched");
		data.put("data", productList);
		return data;
	}

	@Override
	public Product getProductById(Integer id)
	{
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent())
		{
			throw new CustomException("Product of id :: " + id + " not present", ErrorCode.NOT_FOUND);
		}
		return productRepository.getById(id);
	}

	@Override
	public String updateProduct(Integer id, ProductModel productModel)
	{

		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent())
		{
			throw new CustomException("Product with id :: " + id + " not found", ErrorCode.NOT_FOUND);
		}

		Product oldProduct = productOptional.get();

		oldProduct.setName(productModel.getName());
		oldProduct.setCategory(productModel.getCategory());
		oldProduct.setBrand(productModel.getBrand());
		oldProduct.setModelNumber(productModel.getModelNumber());
		oldProduct.setPrice(productModel.getPrice());
		oldProduct.setDescription(productModel.getDescription());

		try
		{
			productRepository.save(oldProduct);
		}
		catch (Exception e)
		{
			throw new CustomException("Unable update product", null);
		}
		
		return "Product with ID :: " + id + " updated !";
	}

	@Override
	public String deleteProduct(Integer id)
	{

		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent())
		{
			throw new CustomException("Product with id :: " + id + " not found", ErrorCode.NOT_FOUND);
		}
		
		try
		{
			productRepository.deleteById(id);
		}
		catch (Exception ex)
		{
			throw new CustomException("Unable to delete product", null);
		}
		
		return "Product with ID :: " + id + " deleted !";
	}

}
