package com.edix.restful.zaps.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	GroupedOpenApi publicApi() {
	return GroupedOpenApi.builder()
	.group("public-apis")
	.pathsToMatch("/xx*")
	.build();
	}
	/*
	@Bean
	OpenAPI customOpenAPI() {
	return new OpenApi()
	.info(new Info().title("API title").version("API version"))
	.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
	.components(
	new Components()
	.addSecuritySchemes("bearerAuth", new SecurityScheme()
	.type(SecurityScheme.Type.HTTP)
	.scheme("bearer")
	.bearerFormat("JWT")));


}*/
}