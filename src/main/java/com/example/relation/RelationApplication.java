package com.example.relation;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RelationApplication {
	
	private static final String str = "\n\n\n\n" + 
			"███╗   ███╗ █████╗ ██╗     ██╗██╗  ██╗    ██████╗  ██████╗  ██████╗ ████████╗\n" + 
			"████╗ ████║██╔══██╗██║     ██║██║ ██╔╝    ██╔══██╗██╔═══██╗██╔═══██╗╚══██╔══╝\n" + 
			"██╔████╔██║███████║██║     ██║█████╔╝     ██████╔╝██║   ██║██║   ██║   ██║   \n" + 
			"██║╚██╔╝██║██╔══██║██║     ██║██╔═██╗     ██╔══██╗██║   ██║██║   ██║   ██║   \n" + 
			"██║ ╚═╝ ██║██║  ██║███████╗██║██║  ██╗    ██████╔╝╚██████╔╝╚██████╔╝   ██║   \n" + 
			"╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═╝    ╚═════╝  ╚═════╝  ╚═════╝    ╚═╝   \n" + 
			"                                                                             \n" + 
			"\nfrom Indus Net Technologies\n\n\n\n";

	public static void main(String[] args) {
		//SpringApplication.run(RelationApplication.class, args);
		SpringApplication application = new SpringApplication(RelationApplication.class);
		application.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.print(str);
			}
			
		});
		application.run(args);
	}

}
