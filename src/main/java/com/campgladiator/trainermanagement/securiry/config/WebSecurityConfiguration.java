package com.campgladiator.trainermanagement.securiry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http.headers().frameOptions().sameOrigin()
    		.and()
    		.csrf().disable()
    	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.authorizeRequests()
    		.antMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**").permitAll()
    		.antMatchers("/trainers/**").authenticated()
    		.and()
    		.httpBasic();
    }
}
