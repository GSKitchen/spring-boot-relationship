package com.example.relation.config.firebase;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.relation.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

@Component
public class FirebaseFilter extends OncePerRequestFilter{

	private static String HEADER_NAME = "X-Authorization-Firebase";
	
	private FirebaseService firebaseService;

//	public FirebaseFilter(FirebaseService firebaseService) {
//		this.firebaseService = firebaseService;
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String xAuth = request.getHeader(HEADER_NAME);
		System.out.println("in firebasefilter");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(xAuth);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		if(xAuth.isEmpty()) {
			System.out.println("xAuth if ->firebasefilter");
			filterChain.doFilter(request, response);
			return;
		}else {
			System.out.println("xAuth else ->firebasefilter");
			try {
				System.out.println("in try block------ parsing token");
				//FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);
				
				//put userdetails
				FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(xAuth);
				UserRecord userRecord = FirebaseAuth.getInstance().getUser(decodedToken.getUid());
				System.out.println("parsing end");
				//String userName = holder.getUid();
				
				String userName = decodedToken.getUid();
				System.out.println(decodedToken.getEmail());
				
				System.out.println(userRecord.getProviderData());
				
				//Authentication auth = new FirebaseAuthenticationToken(userName, userRecord);
				Authentication auth = new FirebaseAuthenticationToken(null, userName, userRecord);
				SecurityContextHolder.getContext().setAuthentication(auth);
				filterChain.doFilter(request, response);
			}catch(FirebaseTokenInvalidException e) {
				throw new SecurityException(e);
			} catch (FirebaseAuthException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
