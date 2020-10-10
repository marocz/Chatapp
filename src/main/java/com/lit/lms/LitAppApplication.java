package com.lit.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LitAppApplication {
	

    
	public static void main(String[] args) {
		SpringApplication.run(LitAppApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{    return new BCryptPasswordEncoder(); }


    
   
}
