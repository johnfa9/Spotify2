package com.ga.dao;

import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  private UserRoleDao userRoleDao;

  @Override
  public User signup(User user) {


    String roleName = user.getUserRole().getName();
    UserRole userRole = userRoleDao.getRole(roleName);

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();

      user.setUserRole(userRole);

      session.save(user);

      session.getTransaction().commit();
    } finally {
      session.close();
    }

    return user;
  }

  @Override
  public User login(User user) {
    User loginUsers = null;

    try (Session session = sessionFactory.getCurrentSession();) {
      session.beginTransaction();

      loginUsers = (User) session
          .createQuery("FROM User WHERE username='" + user.getUsername() + "'").getSingleResult();
    } catch (NoResultException e) {
      System.out.println("No result is found.");
      return null;
    }
    return loginUsers;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> listUsers() {
    List<User> allUsers = null;

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();

      allUsers = session.createQuery("FROM User").getResultList();
    } finally {
      session.close();
    }

    return allUsers;
  }

  @Override
  public User getUserByUsername(String username) {
    User user = null;

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();

      user = (User) session.createQuery("FROM User u WHERE u.username = '" + username + "'")
          .uniqueResult();
    } finally {
      session.close();
    }

    return user;
  }

  @Override
  public User addSong(String username, Long songId) {
    User user = null;
    Song song = null;

    try (Session session = sessionFactory.getCurrentSession();) {
      session.beginTransaction();

      user = (User) session.createQuery("FROM User u WHERE u.username = '" + username + "'")
          .uniqueResult();
      song = session.get(Song.class, songId);

      if(user.getSongList().contains(song) == false){
        user.addSong(song);

        session.update(user);

        session.getTransaction().commit();
      } else {
        System.out.println("warning: duplicate song");
      }


    }
    return user;
  }

}
