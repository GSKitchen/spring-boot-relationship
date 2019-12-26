package com.example.relation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.model.User;
import com.example.relation.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/user")
	public List<User> getUserList(){
		return userRepository.findAll();
	}
}
