package com.example.relation.service;

import com.example.relation.config.firebase.FirebaseTokenHolder;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseToken;

public class FirebaseParser {
	public FirebaseTokenHolder parseToken(String idToken) throws Exception {
		if (idToken.isEmpty()) {
			throw new IllegalArgumentException("FirebaseTokenBlank");
		}
		try {
			//FirebaseToken authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);

			//Tasks.await(authTask);

			return new FirebaseTokenHolder(FirebaseAuth.getInstance().verifyIdToken(idToken).getUid());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
