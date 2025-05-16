package com.fitime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.util.Jwt;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		if(Jwt.getPriKey()==null) {
			Jwt.setPriKey();
		}
		
	}

}
