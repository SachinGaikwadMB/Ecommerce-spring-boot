package com.mb.api.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.api.business.exception.CustomException;
import com.mb.api.enums.ErrorCode;
import com.mb.api.persistance.entity.Cart;
import com.mb.api.persistance.entity.Product;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.CartRepository;
import com.mb.api.persistance.repository.ProductRepository;
import com.mb.api.persistance.repository.UserRepository;
import com.mb.api.web.model.AddToCartModel;

@Service
public class CartServiceImpl implements CartService
{
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String addTocart(AddToCartModel addToCartModel)
	{
		Optional<Product> optionalProduct = productRepository.findById(addToCartModel.getProductId());
		Optional<User> optionalUser = userRepository.findById(addToCartModel.getUserId());

		if (!optionalUser.isPresent())
		{
			throw new CustomException("User not exists", ErrorCode.USER_NOT_FOUND);
		}

		if (!optionalProduct.isPresent())
		{
			throw new CustomException("Product not found", ErrorCode.NOT_FOUND);
		}

		if (cartRepository.existsByProductId(addToCartModel.getProductId()))
		{
			throw new CustomException("Product already in cart", ErrorCode.PRODUCT_ALREADY_EXISTS);
		}

		Product product = optionalProduct.get();
		User user = optionalUser.get();

		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setQuantity(addToCartModel.getQuantity());
		cartRepository.save(cart);

		return "Product added to cart";
	}

	@Override
	public List<Product> getAllCartItems()
	{
		List<Product> cartItemsList = new ArrayList<>();

		List<Cart> cartList = cartRepository.findAll();

		for (int idx = 0; idx < cartList.size(); idx++)
		{
			Product product = cartList.get(idx).getProduct();
			cartItemsList.add(product);
		}

		return cartItemsList;
	}

	@Override
	public String removeItemFromCart(Integer id)
	{
		Optional<Cart> optinalProduct = cartRepository.findByProductId(id);

		if (!optinalProduct.isPresent())
		{
			throw new CustomException("This product not present in cart", ErrorCode.NOT_FOUND);
		}

		cartRepository.deleteById(optinalProduct.get().getId());

		return "Product Remove from Cart";
	}

}
