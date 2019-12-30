package com.example.relation.config.firebase;

import java.util.ArrayList;

import com.google.api.client.util.ArrayMap;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseTokenHolder {
	private FirebaseToken token;
	private String uid;

	public FirebaseTokenHolder(FirebaseToken token) {
		this.token = token;
	}
	
	public FirebaseTokenHolder(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return token.getEmail();
	}

	public String getIssuer() {
		return token.getIssuer();
	}

	public String getName() {
		return token.getName();
	}

	public String getUid() {
		return token.getUid();
	}
	
	public String getGoogleId() {
		String userId = ((ArrayList<String>) ((ArrayMap) ((ArrayMap) token.getClaims()
				.get("firebase")).get("identities")).get("google.com")).get(0);
		return userId;
	}
}
