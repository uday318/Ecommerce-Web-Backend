package com.ecom.Entities;

import java.time.LocalDate;

import com.ecom.DTO.OrderStatusEnum;
import com.ecom.DTO.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderId;

	private LocalDate orderDate;

	@ManyToOne
	@JsonIncludeProperties({"title"})
	private Product product;

	private Double price;

	private Integer quantity;

	@ManyToOne
	@JsonIncludeProperties({"name"})
	private User user;
	
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum status;
	
	@Enumerated(EnumType.STRING)
	private PaymentTypeEnum paymentType;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIncludeProperties({"city" , "pincode"})
	private OrderAddress orderAddress;

	public ProductOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOrder(Long id, String orderId, LocalDate orderDate, Product product, Double price, Integer quantity,
			User user, OrderStatusEnum status, PaymentTypeEnum paymentType, OrderAddress orderAddress) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.user = user;
		this.status = status;
		this.paymentType = paymentType;
		this.orderAddress = orderAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public PaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEnum paymentType) {
		this.paymentType = paymentType;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	

}
