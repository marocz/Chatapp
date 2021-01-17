package com.chatproject.marocz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatAppApplication {
	

    
	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{    return new BCryptPasswordEncoder(); }
   
}
