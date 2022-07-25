package com.mb.api.web.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.service.ProductService;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.ProductModel;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductModel productModel)
	{
		String responseMessage = productService.saveProduct(productModel);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllProducts()
	{
		Map<String, Object> data = productService.getAllProducts();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id)
	{
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@Valid @PathVariable Integer id, @RequestBody ProductModel productModel)
	{

		String responseMessage = productService.updateProduct(id, productModel);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id)
	{
		String responseMessage = productService.deleteProduct(id);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);

	}

	// Search product by name or category
	@GetMapping("/{}")
	public String searchByNameOrCategory()
	{
		return "search query working ...from controller";
	}

}
