package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;

//There are two ways to talk to the database in Spring Security: jdbcAuthentication and userDetailsService.
public interface UserService extends UserDetailsService{  //jfa added extends statement 10 16
  //public User signup(User user);  jfa removed 10 16
  //public User login(User user); //jfa removed 10 16
  public String login(User user); //jfa added 10 16  will return a token
  public String signup(User user);  //jfa added 10 16
  
  public List<User> listUsers();
  public User getUserByUsername(String username);
  
}
