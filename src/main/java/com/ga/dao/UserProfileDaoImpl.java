package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  UserDao userDao;

  @Override
  public UserProfile createProfile(String username, UserProfile userProfile) {
    User user = userDao.getUserByUsername(username);
    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();
      session.save(userProfile);
      user.setUserProfile(userProfile);
      session.update(user);

      session.getTransaction().commit();

    } finally {
      session.close();
    }

    return userProfile;
  }
}
