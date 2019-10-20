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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ga.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.ga")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  @Override
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//    auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
//  }
  
  @Autowired
  private JwtRequestFilter jwtRequestFilter;
  
  @Autowired
  UserService userService;
  
  @Bean("encoder")
  public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
    .authorizeRequests()
    .antMatchers("/user/signup/**", "/user/login/**").permitAll()
    .antMatchers("/user/**", "/song/**", "/profile/**").authenticated()        
    .antMatchers("/role/**").hasRole("ADMIN")
    .and()
    .httpBasic()
    .and()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
  

  
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService);
  }
}
