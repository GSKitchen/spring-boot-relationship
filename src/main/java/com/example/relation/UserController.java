package com.example.relation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.model.User;
import com.example.relation.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/users")
	public List<User> getUserList(){
		return userRepository.findAll();
	}
	
	@GetMapping("/firebase-user/{uid}")
	public UserRecord getFirebaseUser(@PathVariable String uid) throws FirebaseAuthException{
		UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
		System.out.println("------------------------------------------");
		System.out.println("Success!\nEmail: " + userRecord.getEmail());
		System.out.println("------------------------------------------");
		return userRecord;
	}
	
	@GetMapping("/token/{token}")
	public String getUserFromToken(@PathVariable String token) throws FirebaseAuthException {
		String str = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQ3NjM1YWI2NDZlMDdmZDE5OWY3NGIwMTZhOTU0MzkyMmEwY2ZmOWEiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiU2FiYmlyIEFoYW1lZCBTaGFpa2giLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EtL0FBdUU3bUNPY0t0TkdZNS1TaXgyUWJWWXdrVXN1QnVfZDBTLUNrM1lKZGlmIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL2Zpci1hdXRoLTQwMDZiIiwiYXVkIjoiZmlyLWF1dGgtNDAwNmIiLCJhdXRoX3RpbWUiOjE1NzczNjY0MTYsInVzZXJfaWQiOiJiVUF0b3p6WnZvaGtURWI0a21aMGpONGVxVW4yIiwic3ViIjoiYlVBdG96elp2b2hrVEViNGttWjBqTjRlcVVuMiIsImlhdCI6MTU3NzM2NzMyNCwiZXhwIjoxNTc3MzcwOTI0LCJlbWFpbCI6InNhYmJpcmEuc2hhaWtoQGluZHVzbmV0LmNvLmluIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMTUxMTE2ODYzNjk5MTg2NTU4MjIiXSwiZW1haWwiOlsic2FiYmlyYS5zaGFpa2hAaW5kdXNuZXQuY28uaW4iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.lfI0xjCVTF3vdJ_9HHi1VBBXx-KOMdvueW7LbN52yH3JuWtZQYyCr7rH5-Zc6qo1DO1AhjW_QjhKDuJO69nui_ULCDybPSyRv6Fa1VSCQzoPRPy3S9uxFGg5OYDMwDXE4IibufbzoQC2I_KRp5I9eRJfSoSYKtzhUaht9prazawa17-KbvrVuplxau5Hj_ePBeVXJe4jFmwI2_x62wRzKLYm3jI5zAShaUOAtUhu7BpVTuPHDRdBNPBCAeEAqNamXF3JT8vmlmNdiHzZ2gIrYgnAdWkTDKS0CPmGzZOuwd5EfYkqjqNFnZHrH9KPIDyMQnTjl8suy16bmG4n0NmDYw";
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(str);
		System.out.println("------------------------------------------");
		System.out.println("The uid: " + decodedToken.getUid() + "\nEmail: " + decodedToken.getEmail());
		System.out.println("------------------------------------------");
		return decodedToken.getUid().toString();
	}
}
