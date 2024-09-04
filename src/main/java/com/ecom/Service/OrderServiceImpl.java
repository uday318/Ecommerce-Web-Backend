package com.ecom.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.DTO.OrderRequest;
import com.ecom.DTO.OrderStatusEnum;
import com.ecom.Entities.Cart;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.OrderAddress;
import com.ecom.Entities.ProductOrder;
import com.ecom.Repository.CartRepository;
import com.ecom.Repository.ProductOrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductOrderRepository orderRepo;

	@Override
	public GenericResponseEntity saveOrder(OrderRequest orderRequest ,Long userId)  {

		List<Cart> carts = cartRepo.findByUserId(userId);

		for (Cart cart : carts) {

			ProductOrder order = new ProductOrder();
			
			order.setOrderId(orderNumberGenerate());
			order.setOrderDate(LocalDate.now());

			order.setProduct(cart.getProduct());
			order.setPrice(cart.getProduct().getDiscountPrice());

			order.setQuantity(cart.getQuantity());
			order.setUser(cart.getUser());

			order.setStatus(OrderStatusEnum.IN_PROGRESS);
			order.setPaymentType(orderRequest.getPaymentType());

			OrderAddress address = new OrderAddress();
			address.setFirstName(orderRequest.getFirstName());
			address.setLastName(orderRequest.getLastName());
			address.setEmail(orderRequest.getEmail());
			address.setMobileNo(orderRequest.getMobileNo());
			address.setAddress(orderRequest.getAddress());
			address.setCity(orderRequest.getCity());
			address.setState(orderRequest.getState());
			address.setPincode(orderRequest.getPincode());

			order.setOrderAddress(address);

			orderRepo.save(order);
		}
		return new GenericResponseEntity(201, "Order created successfully.");
	}
	
	public String orderNumberGenerate() {
		String orderNumber = "ORD" + System.currentTimeMillis();
		return orderNumber;
	}
	
	@Override
	public List<ProductOrder> getAllOrders() {
		return orderRepo.findAll();
	}


	@Override
	public ProductOrder getOrderById(String orderId) {
		return orderRepo.findByOrderId(orderId);
	}

	@Override
	public GenericResponseEntity deleteOrder(String orderId) {
		ProductOrder order = orderRepo.findByOrderId(orderId);
		orderRepo.delete(order);
		return new GenericResponseEntity(202, "Order deleted successfully.");
	}
}
