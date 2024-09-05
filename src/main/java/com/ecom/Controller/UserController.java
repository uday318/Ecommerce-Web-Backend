package com.ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.DTO.OrderRequest;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.User;
import com.ecom.Service.CartService;
import com.ecom.Service.OrderService;
import com.ecom.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/register")
	public ResponseEntity<GenericResponseEntity> createUser(@RequestBody User user) {
		GenericResponseEntity gre = userService.registerUser(user);
		return new ResponseEntity<>(gre, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	@Transactional
	public String login(@RequestBody User user) {
		return userService.verify(user);
	}
	
	@PutMapping("/update-profile/{userId}")
	public ResponseEntity<GenericResponseEntity> updateUser(@RequestBody User user, @PathVariable Long userId) {
		GenericResponseEntity gre = userService.updateUser(user, userId);
		return new ResponseEntity<>(gre, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/addCart")
	public  ResponseEntity<GenericResponseEntity> addToCart(@RequestParam Integer pid,
			@RequestParam Integer uid, HttpSession session) {
		
		GenericResponseEntity gre = cartService.saveCart(pid, uid);
		return new ResponseEntity<>(gre, HttpStatus.CREATED);
	}
	
	@PostMapping("/save-order/{userId}")
	public ResponseEntity<GenericResponseEntity> saveOrder(@RequestBody OrderRequest request,@PathVariable Long userId) {
		GenericResponseEntity gre = orderService.saveOrder(request , userId);
		return new ResponseEntity<>(gre, HttpStatus.CREATED);
	}


}
