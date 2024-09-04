package com.ecom.Service;

import java.util.List;

import com.ecom.Entities.GenericResponseEntity;
import com.ecom.Entities.User;

public interface UserService {

	GenericResponseEntity registerUser(User user);

	GenericResponseEntity updateUser(User user, Long userId);

	GenericResponseEntity allowUserOrNot(Long id, Boolean isActive);

	GenericResponseEntity deleteUser(Long userId);

	User getUserById(Long userId);

	List<User> getAllUsers();

}
