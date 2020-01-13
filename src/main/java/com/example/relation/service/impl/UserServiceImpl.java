package com.example.relation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.relation.model.User;
import com.example.relation.repository.UserRepository;
import com.example.relation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public User findById(Long userId) {
		String key = "user_" + userId;
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		boolean hasKey = redisTemplate.hasKey(key);
		if(hasKey) {
			User user = operations.get(key);
			System.out.println("user from cache" + key);
			return user;
		}
		User user = userRepository.findById(userId).get();
		operations.set(key, user);
		System.out.println("insert user into redis " + key);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

}
