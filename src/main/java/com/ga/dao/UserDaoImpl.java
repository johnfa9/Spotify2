package com.ga.dao;

import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  SessionFactory sessionFactory;
  
  @Override
  public User signup(User user) {
    Session session = sessionFactory.getCurrentSession();
    
    try{
      session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
    }finally{
      session.close();
    }
    
    return user;
  }

  @Override
  public User login(User user) {
    User loginUsers = null;

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();

      loginUsers = (User) session.createQuery("FROM User WHERE username='" + user.getUsername()
          + "' AND password='" + user.getPassword() + "'").getSingleResult();
    } catch (NoResultException e) {
      System.out.println("No result is found.");
      return null;
    } finally {
      session.close();
    }
    return loginUsers;

  }

}
