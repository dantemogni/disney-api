package com.alkemy.disney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.alkemy.*")
public class ChallengeDisneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDisneyApplication.class, args);
	}

}
