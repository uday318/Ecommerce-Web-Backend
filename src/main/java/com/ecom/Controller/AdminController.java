package com.ecom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Entities.Category;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.Product;
import com.ecom.Entities.ProductOrder;
import com.ecom.Entities.User;
import com.ecom.Service.CategoryService;
import com.ecom.Service.OrderService;
import com.ecom.Service.ProductService;
import com.ecom.Service.UserService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@PutMapping("/isActivate/{id}")
	@Transactional
	public ResponseEntity<GenericResponseEntity> allowUserOrNot(@PathVariable Long id, @RequestParam Boolean isActive) {
		GenericResponseEntity gre = userService.allowUserOrNot(id, isActive);
		return new ResponseEntity<>(gre, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{userId}")
	public GenericResponseEntity deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);

	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	//-------------------------Category API---------------------------------//
	
	@PostMapping("/create")
	public ResponseEntity<GenericResponseEntity> createCategory(@RequestBody Category category) {
		GenericResponseEntity createCategory = this.categoryService.createCategory(category);
		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<GenericResponseEntity> updateCategory(@RequestBody Category category,
			@PathVariable Long categoryId) {
		GenericResponseEntity updatedCategory = this.categoryService.updateCategory(category, categoryId);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/deleteCat/{categoryId}")
	public GenericResponseEntity deleteCategory(@PathVariable Long categoryId) {
		return categoryService.deleteCategory(categoryId);
		
	}
	
	@GetMapping("/getCatById/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Category>> getCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	//---------------------------Product API's------------------------------------------//
	
	@PostMapping("/addProduct")
	public ResponseEntity<GenericResponseEntity> addProduct(@RequestBody Product product) {
		GenericResponseEntity saveProduct = this.productService.addProduct(product);
		return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<GenericResponseEntity> updateProduct(@RequestBody Product product,
			@PathVariable Long productId) {
		GenericResponseEntity updatedProduct = this.productService.updateProduct(product, productId);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public GenericResponseEntity deleteProduct(@PathVariable Long productId) {
		return productService.deleteProduct(productId);
		
	}
	
	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
		return ResponseEntity.ok(this.productService.getProduct(productId));
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(this.productService.getAllProducts());
	}
	
	//---------------------------------order------------------------------------//
	
	@GetMapping("/getOrder/{orderId}")
	public ResponseEntity<ProductOrder> getOrderById(@PathVariable String orderId) {
		return ResponseEntity.ok(this.orderService.getOrderById(orderId));
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<List<ProductOrder>> getAllOrders() {
		return ResponseEntity.ok(this.orderService.getAllOrders());
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public GenericResponseEntity deleteOrder(@PathVariable String orderId) {
		return orderService.deleteOrder(orderId);
		
	}
}
