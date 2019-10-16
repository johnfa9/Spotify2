package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ga.dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{
  
  @Autowired
  UserDao userDao;

  @Override
  public User signup(User user) {
    return userDao.signup(user);
  }

  @Override
  public User login(User user) {
    return userDao.login(user);
  }
  
  
}
