package com.example.bookmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
		     .authorizeHttpRequests(auth->auth
		    		 .requestMatchers("/users/register", "/h2-console/**").permitAll()
		    		 .requestMatchers(HttpMethod.GET, "/books/register").permitAll()
		    		 .requestMatchers(HttpMethod.POST, "/books/register").permitAll()
		    		 .requestMatchers("/register").permitAll()
		    		 .anyRequest().authenticated()
		    		 )
		     .formLogin(form->form
		    		 .loginPage("/login")
		    		 .defaultSuccessUrl("/books", true)
		    		 .permitAll()
		    		 )
		     .logout(logout->logout
		    		 .logoutSuccessUrl("/login?logout")
		    		 .permitAll()
		    		 )
		     .csrf(csrf->csrf
		    		 .ignoringRequestMatchers("/h2-console/**")
		    		 //.disable()
		    		 )
		     .headers(headers->headers
		    		 .frameOptions(frameOptions->frameOptions.disable())
		    		 );
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
