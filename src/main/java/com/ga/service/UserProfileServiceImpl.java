package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ga.dao.UserProfileDao;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{

  @Autowired
  UserProfileDao userProfileDao;
  
  @Override
  public UserProfile createProfile(String username, UserProfile userProfile) {
    return userProfileDao.createProfile(username, userProfile);
  }
  
}
