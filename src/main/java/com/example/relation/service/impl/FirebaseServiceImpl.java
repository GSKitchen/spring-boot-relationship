package com.example.relation.service.impl;

import org.springframework.stereotype.Service;

import com.example.relation.config.firebase.FirebaseTokenHolder;
import com.example.relation.service.FirebaseParser;
import com.example.relation.service.FirebaseService;

public class FirebaseServiceImpl implements FirebaseService{

	@Override
	public FirebaseTokenHolder parseToken(String idToken) throws Exception {
		return new FirebaseParser().parseToken(idToken);
	}
}
