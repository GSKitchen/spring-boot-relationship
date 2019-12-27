package com.example.relation.service;

import com.example.relation.config.firebase.FirebaseTokenHolder;

public interface FirebaseService {
	FirebaseTokenHolder parseToken(String idToken);
}
