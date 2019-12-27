package com.example.relation.config.firebase;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken{
	private static final long serialVersionUID = -1869548136546750302L;
	private final Object principal;
	private Object credentials;
	
	public FirebaseAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
	
	public FirebaseAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal,
			Object credentials) {
		
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	public Object getCredentials() {
		return credentials;
	}

	public Object getPrincipal() {
		return principal;
	}
	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
		if(isAuthenticated) {
			throw new IllegalArgumentException("cannto sent this to trusteddd.");
		}
		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		credentials = null;
	}
	
}
