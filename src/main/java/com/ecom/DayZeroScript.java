package com.ecom;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecom.DTO.RoleEnum;
import com.ecom.Entities.User;
import com.ecom.Repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DayZeroScript {

	@Autowired
	private UserRepository userRepo;

	@PostConstruct
	public void init() throws IOException {

		// create Admin USer
		User user = new User();
		user.setEmail("udaypatil318@gmail.com");
		user = createAdminUserIfNotFound(user);
	}

	private User createAdminUserIfNotFound(User user) {
		
		User dbUser = userRepo.findByEmail(user.getEmail());
		if (dbUser == null) {
			user.setName("Uday Patil");
			user.setPassword("Root@123");
			user.setMobileNo("7447338862");
			user.setRole(RoleEnum.ADMIN);
			user.setProfileImage("default.png");
			user.setAddress("Near ASC Collage");
			user.setCity("Ichalkaranji");
			user.setPincode("416115");
			user.setState("Maharastra");
			user.setIsActive(true);
			encodePassword(user);
			user = userRepo.saveAndFlush(user);
			return user;
		}
		return dbUser;
	}
	
	private void encodePassword(User user) {
		final String plainPassword = user.getPassword();
		user.setPassword(new BCryptPasswordEncoder(12).encode(plainPassword));
	}

}
