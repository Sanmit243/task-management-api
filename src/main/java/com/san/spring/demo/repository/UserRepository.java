package com.san.spring.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.san.spring.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//here is the JPA magic, just name a method, spring will take care of operation def
	Optional<User> findByEmail(String email);
}
