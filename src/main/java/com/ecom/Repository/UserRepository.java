package com.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String emailId);

	User findByMobileNo(String mobileNo);

	Optional<User> findById(Integer userId);

}
