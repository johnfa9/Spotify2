package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.dao.UserProfileDao;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{

  @Autowired
  UserDao userDao;
  
  @Autowired
  UserProfileDao userProfileDao;
  
  @Autowired
  UserService userService;
  
  @Override
  public UserProfile createProfile(String username, UserProfile userProfile) {
    
    UserDetails userDetails = userService.loadUserByUsername(username);
    System.out.println(userDetails.getUsername());
    
    User user = userDao.getUserByUsername(username);
    
    if(user.getUserProfile() != null){
      System.out.println("profile exist, please use update");
    }else{
      return userProfileDao.createProfile(username, userProfile);
    }
    return user.getUserProfile();   
  }
  
}
