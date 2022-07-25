package com.mb.api.persistance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.api.persistance.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>
{
	 boolean existsByProductId(Integer id);
	 Optional<Cart> findByProductId(Integer id);
}
