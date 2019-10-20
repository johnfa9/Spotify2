package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.dao.UserProfileDao;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService {

  private UserDao userDao;

  private UserProfileDao userProfileDao;

  @Autowired
  public UserProfileServiceImpl(UserDao userDao, UserProfileDao userProfileDao) {
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
  }

  @Override
  public UserProfile createProfile(String username, UserProfile userProfile) {

    User user = userDao.getUserByUsername(username);

    if (user.getUserProfile() != null) {
      System.out.println("profile exist, please use update");
    } else {
      return userProfileDao.createProfile(username, userProfile);
    }
    return user.getUserProfile();
  }

  @Override
  public UserProfile getUserProfile(String username) {

    return userProfileDao.getUserProfile(username);
  }

}
