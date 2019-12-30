package com.example.relation.config.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.relation.exception.ResourceNotFoundException;

public class FirebaseAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userService;
	
	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		if(!supports(authentication.getClass())) {
			return null;
		}
		
		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
		UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
		if(details == null) {
			//throw new FirebaseUserNotExistsException();
			throw new ResourceNotFoundException("firebase user not found");
		}
		
		authenticationToken = new FirebaseAuthenticationToken(details.getAuthorities(), details, authentication.getCredentials());
		return authenticationToken;
	}
}
