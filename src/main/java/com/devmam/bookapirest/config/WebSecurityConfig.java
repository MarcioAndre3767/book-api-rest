
package com.devmam.bookapirest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void coinfigureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
			.password("{noop}admin").roles("ADMIN")
		.and().withUser("user")
		.password("{noop}user").roles("USER");		
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()			
				.and()
					.httpBasic()			//autenticação básica
				.and()
					.csrf().disable(); 	//desabilitado para ataque de terceiros
	}	
	

}

