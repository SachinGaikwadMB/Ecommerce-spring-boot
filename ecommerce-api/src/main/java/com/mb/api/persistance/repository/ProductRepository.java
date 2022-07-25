package com.mb.api.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.api.persistance.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	 boolean existsBymodelNumber(String modelNumber);
 
	 //TODO : filter functionalities
	 
	 
}
