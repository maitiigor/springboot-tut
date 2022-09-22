package com.dailycodebuffer.SpringBoot.Tutorial;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.User;
import com.dailycodebuffer.SpringBoot.Tutorial.service.UserService;

@SpringBootApplication
public class SpringBootTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService){
		return args->{
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_USER"));
			
			userService.saveUser(new User(null, "John", "Blue", "password", "johnblue@mail.com", new ArrayList<>()));
			userService.saveUser(new User(null, "Luke", "Mark", "password", "lukemark@mail.com", new ArrayList<>()));

			userService.addUserRole("johnblue@mail.com", "ROLE_ADMIN");
			userService.addUserRole("lukemark@mail.com", "ROLE_USER");
			
		};
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}

}
