package com.ga.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{
  
  @Autowired
  UserDao userDao;
  
  @Autowired   //jfa added 10 16
  @Qualifier("encoder")  //jfa added 10 16
  PasswordEncoder bCryptPasswordEncoder;  //jfa added 10 16  BCrypt internally generates a random salt.

  @Autowired   //jfa added 10 16
  JwtUtil jwtUtil;   //jfa added 10 16
  
  //jfa added 10 16-----------
//  @Override
//	public String login(User user) {
//		if(userDao.login(user) != null) {
//          		UserDetails userDetails = loadUserByUsername(user.getUsername());
//          		
//			return jwtUtil.generateToken(userDetails);
//      	}
//      
//		return null;
//	}
  
//jfa added 10 16-----------------  
  @Override
	public String login(User user) {
		User foundUser = userDao.login(user);
		if(foundUser != null && 
				foundUser.getUserId() != null && 
				bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
		    UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
		    
		    return jwtUtil.generateToken(userDetails);
		}
      	
		return null;
	}
  //---------------------
  
  //-------------------------------
//jfa  removed 10 16
 //  @Override
//  public User signup(User user) {
//    return userDao.signup(user);
//  }
  
  //jfa added 10 16--------------
//  @Override
//	public String signup(User user) {
//      	if(userDao.signup(user).getUserId() != null) {
//          		UserDetails userDetails = loadUserByUsername(user.getUsername());
//          		
//			return jwtUtil.generateToken(userDetails);
//      	}
//      	
//		return null;
//  }
  //---------------------------------------
  
  //jfa added 10/16
  @Override
	public String signup(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		if(userDao.signup(user).getUserId() != null) {
		    UserDetails userDetails = loadUserByUsername(user.getUsername());
		    
		    return jwtUtil.generateToken(userDetails);
		}
		
		return null;
	}
  //------------------------------
  
  // jfa removed 10 16
 // @Override
  //public User login(User user) {
  //  return userDao.login(user);
  //}
  //-----------
  
  // jfa added 10 16-----------------------------------we'll add a method to load user information for a supplied username.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //loadUserByUsername() by default to check against the user data.
      User user = userDao.getUserByUsername(username);

      if(user==null)
          throw new UsernameNotFoundException("Unkknown user: " +username);

      return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
              true, true, true, true, getGrantedAuthorities(user));
  }
  
  private List<GrantedAuthority> getGrantedAuthorities(User user) {
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

      authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

      return authorities;
  }
  //----------------------------------------------------------------
  
  @Override
  public List<User> listUsers(){
	  return userDao.listUsers();
  }
  
  @Override
  public User getUserByUsername(String username) {
	  return userDao.getUserByUsername(username);
  }

}
