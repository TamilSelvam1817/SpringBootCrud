package com.example.demo.config;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.authorizeHttpRequests(authz ->
		  authz.requestMatchers(HttpMethod.POST,"/api/user").permitAll()
		  .requestMatchers("/api/user/**").authenticated()
		  .anyRequest().permitAll()
		  ).formLogin(form -> form.permitAll().defaultSuccessUrl("/dashboard"))
		   .csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	/*
	@Bean
	public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("Kohli")
				.password(passwordEncoder.encode("user123"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("Virat")
				.password(passwordEncoder.encode("admin123"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/
	
	@Bean
	public UserDetailsService userDetailService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
