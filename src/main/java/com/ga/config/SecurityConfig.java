package com.ga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ga.service.UserService;

//import com.ga.entity.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //In order for method-level security to take effect, we need to add one more annotation 
@ComponentScan("com.ga")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	@Bean("encoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    private JwtRequestFilter jwtRequestFilter;
	
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {	
//	    User.UserBuilder users = User.withDefaultPasswordEncoder();  //The User object is not the one we created but a security UserDetails user object
//	    
//	    auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  //configure() method ensures that all requests are authenticated with HTTP Basic authentication
	    http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/user/signup/**", "/user/login/**").permitAll()  //order matters
	        .antMatchers("/user/**").authenticated()  // states that this HTTP security will only be applicable to URLs that start with /user/  checks user not role
	        .antMatchers("/role/**").hasRole("DBA")  //first authenticates the user, then checks their role against the authorized role.
//	        .antMatchers("/course/**").hasRole("ADMIN")
	        .and()
	        .httpBasic()
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
