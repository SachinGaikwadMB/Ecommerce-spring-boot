package com.mb.api.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.service.CartService;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.AddToCartModel;

@RestController
@RequestMapping("/api/cart")
public class CartController
{
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addTocart(@RequestBody AddToCartModel addToCartModel) {
		String responseMessage = cartService.addTocart(addToCartModel);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<Product>> getAllCartItems() {
		List<Product> responseBody =  cartService.getAllCartItems();
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable Integer id) {
		String responseMessage = cartService.removeItemFromCart(id);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
	
	
}
