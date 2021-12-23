package com.alkemy.disney.app;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.alkemy.disney.app"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());

		return docket;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Disney API",
				"Challenge Backend JAVA - Alkemy",
				"1.0",
				"https://github.com/dantemogni/disney-api",
				new Contact("Dante Mogni", "https://github.com/dantemogni/", "dantejoaquin.mogni@gmail.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
}
