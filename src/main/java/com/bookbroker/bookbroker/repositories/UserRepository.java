package com.bookbroker.bookbroker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbroker.bookbroker.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	Optional<User> findByEmail(String email);
	 User findByEmail(String email);
}
