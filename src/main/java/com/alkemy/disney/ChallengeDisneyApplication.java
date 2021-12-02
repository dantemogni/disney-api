package com.alkemy.disney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.alkemy.controller", "com.alkemy.service"})
@EntityScan("com.alkemy.entity")
@EnableJpaRepositories("com.alkemy.repository")
public class ChallengeDisneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDisneyApplication.class, args);
	}

}
