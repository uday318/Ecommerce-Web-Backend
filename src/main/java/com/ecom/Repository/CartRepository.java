package com.ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Cart findByProductIdAndUserId(Integer productId, Integer userId);

	public Integer countByUserId(Long userId);

	public List<Cart> findByUserId(Long userId);


}
