package com.example.relation.config;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfiguration {
	
	@Value("${firebase.auth.file}")
	private String firebaseConfigFilePath;
	private String databaseUrl = "https://fir-auth-4006b.firebaseio.com";
	
	@PostConstruct
	public void init() throws IOException {
		FileInputStream serviceAccount = new FileInputStream(firebaseConfigFilePath);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl(databaseUrl)
				.build();
		
		FirebaseApp.initializeApp(options);
	}
}
