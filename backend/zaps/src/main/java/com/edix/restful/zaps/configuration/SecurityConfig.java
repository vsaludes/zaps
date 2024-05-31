package com.edix.restful.zaps.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edix.restful.zaps.security.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;



import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> 
                csrf
                .disable())
				//.cors(withDefaults())
            .authorizeHttpRequests(authRequest ->
              authRequest
              	.requestMatchers(HttpMethod.GET).permitAll()//cuidado, no deberia dejar hacer gets de usuarios
              	.requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers("/auth/**").permitAll()
                //.requestMatchers("/api/usuarios").permitAll() //.hasRole("ADMIN")
                .requestMatchers("/api/productos").permitAll() //.authenticated()
                .anyRequest().authenticated()
                )
            .sessionManagement(sessionManager->
            sessionManager 
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            //.formLogin(withDefaults()) //si se usa tokens se puede eliminar
            .formLogin(form -> form.disable())
            .build();
	}
}
