package com.edix.restful.zaps.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edix.restful.zaps", "com.edix.restful.zapsJwt"})
public class DemoJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJwtApplication.class, args);
	}

}