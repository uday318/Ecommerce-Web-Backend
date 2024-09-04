package com.ecom.Service;

import java.util.List;

import com.ecom.DTO.OrderRequest;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.ProductOrder;

public interface OrderService {

	GenericResponseEntity saveOrder(OrderRequest request, Long userId);

	ProductOrder getOrderById(String orderId);

	List<ProductOrder> getAllOrders();

	GenericResponseEntity deleteOrder(String orderId);

}
