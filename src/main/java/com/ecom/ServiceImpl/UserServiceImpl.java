package com.ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.DTO.RoleEnum;
import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.User;
import com.ecom.Exception.DataValidationException;
import com.ecom.Repository.UserRepository;
import com.ecom.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	// private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public GenericResponseEntity registerUser(User user) {
		if (user.getName() == null || user.getName().isBlank()) {
			throw new DataValidationException("Name required.");
		}
		if (user.getEmail() == null || user.getEmail().isBlank()) {
			throw new DataValidationException("Email required.");
		}

		if (user.getPassword() == null || user.getPassword().isBlank()) {
			throw new DataValidationException("Password Cannot be Null or Blank");
		}
		if (user.getMobileNo() == null || user.getMobileNo().isBlank()) {
			throw new DataValidationException("Mobile no. Required.");
		}
		validateDuplicateUser(user.getEmail());

		// user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(RoleEnum.USER);
		user.setIsActive(true);
		userRepo.save(user);
		return new GenericResponseEntity(201, "User registered Successfully.");

	}

	private void validateDuplicateUser(String emailId) {
		User user = userRepo.findByEmail(emailId);
		if (user != null) {
			throw new DataValidationException("User with same email already exists!");
		}
	}

	@Override
	public GenericResponseEntity updateUser(User user, Long userId) {
		User dbuser = this.userRepo.findById(userId)
				.orElseThrow(() -> new DataValidationException("User Not Available."));

		if (user.getName() == null || user.getName().isBlank()) {
			throw new DataValidationException("Name required.");
		}
		if (user.getEmail() == null || user.getEmail().isBlank()) {
			throw new DataValidationException("Email required.");
		}

		if (user.getPassword() == null || user.getPassword().isBlank()) {
			throw new DataValidationException("Password Cannot be Null or Blank.");
		}
		if (user.getMobileNo() == null || user.getMobileNo().isBlank()) {
			throw new DataValidationException("Mobile no. Required.");
		}

		dbuser.setName(user.getName());
		dbuser.setEmail(user.getEmail());
		dbuser.setMobileNo(user.getMobileNo());
		dbuser.setAddress(user.getAddress());
		dbuser.setCity(user.getCity());
		dbuser.setPincode(user.getPincode());
		dbuser.setState(user.getState());
		dbuser.setPassword(user.getPassword());

		// dbuser.setPassword(encoder.encode(user.getPassword()));

		userRepo.save(dbuser);
		return new GenericResponseEntity(202, "User updated Successfully.");
	}

	@Override
	public GenericResponseEntity allowUserOrNot(Long id, Boolean isActive) {

		User userDB = userRepo.findById(id).get();
		System.out.println("password  " + userDB.getPassword());

		if (userDB != null) {
			userDB.setIsActive(isActive);
			userDB = userRepo.save(userDB);
			if (userDB.getIsActive() == false) {
				return new GenericResponseEntity(202, "User deactivated Successfully");
			} else {
				return new GenericResponseEntity(202, "User activated Successfully");
			}
		}
		return new GenericResponseEntity(202, "User Data Updated Successfully");
	}

	@Override
	public User getUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow(() -> new DataValidationException("User Not Available."));
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public GenericResponseEntity deleteUser(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new DataValidationException("User Not Available."));
		userRepo.delete(user);
		return new GenericResponseEntity(202, "User deleted Successfully.");
	}

}
