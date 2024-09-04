package com.ecom.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.Entities.Cart;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.Product;
import com.ecom.Entities.User;
import com.ecom.Repository.CartRepository;
import com.ecom.Repository.ProductRepository;
import com.ecom.Repository.UserRepository;
import com.ecom.Service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	public GenericResponseEntity saveCart(Integer productId, Integer userId) {

		User userDtls = userRepo.findById(userId).get();
		Product product = productRepo.findById(productId).get();

		Cart cartStatus = cartRepo.findByProductIdAndUserId(productId, userId);

		Cart cart = null;

		if (ObjectUtils.isEmpty(cartStatus)) {
			cart = new Cart();
			cart.setProduct(product);
			cart.setUser(userDtls);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		} else {
			cart = cartStatus;
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		cartRepo.save(cart);
		return new GenericResponseEntity(201, "Added to cart.");

	}

}
