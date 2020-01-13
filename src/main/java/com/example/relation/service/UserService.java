package com.example.relation.service;

import java.util.List;

import com.example.relation.model.User;

public interface UserService {

	public User findById(Long userId);
	
	public List<User> getAllUser();
	
	public User createUser(User user);
}
