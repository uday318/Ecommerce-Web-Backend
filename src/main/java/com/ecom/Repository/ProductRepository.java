package com.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findById(Integer productId);

}
