package com.san.spring.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.san.spring.demo.exception.InvalidCredentialsException;
import com.san.spring.demo.exception.UserAlreadyExistsException;
import com.san.spring.demo.model.LoginRequest;
import com.san.spring.demo.model.User;
import com.san.spring.demo.repository.UserRepository;
import com.san.spring.demo.security.JwtUtil;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public User register(User user) {
		//check if user is already present in db
		userRepository.findByEmail(user.getEmail()).ifPresent((u) -> {
			throw new UserAlreadyExistsException("User Already Exists.");
		});
		
		
		//hash the pass and save it to the db
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	public String login(LoginRequest user) {
		
		Optional<User> loginUser = userRepository.findByEmail(user.getEmail());
		
		if(loginUser.isEmpty()) throw new RuntimeException("Invalid email or password!");
		
		//first check is pass and email correct
		String rawPassword = user.getPassword();
		String hashedPassword = loginUser.get().getPassword();
		
		if(!passwordEncoder.matches(rawPassword, hashedPassword)) {
			throw new InvalidCredentialsException("Invalid email or password!");
		}
		
		//valid user
		return jwtUtil.generateToken(user.getEmail());
	}
}
