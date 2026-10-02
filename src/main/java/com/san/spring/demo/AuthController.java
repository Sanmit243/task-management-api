package com.san.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.san.spring.demo.model.LoginRequest;
import com.san.spring.demo.model.User;
import com.san.spring.demo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	ResponseEntity<User> register(@RequestBody @Valid User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(user));
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody @Valid LoginRequest user) {
		return ResponseEntity.ok(authService.login(user));
	}
	
}
