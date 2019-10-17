package com.ga.dao;

import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	
  //jfa added 10 17----------
	@Override
    public User login(User user) {
        User savedUser = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            savedUser = (User) session.createQuery("FROM User u WHERE u.username =  '" + user.getUsername()+ "'")
                    .getSingleResult();
        } finally {
            session.close();
        }
        return savedUser;
    }
//----------------------------------

  
// jfa delete 10 17  
//  @Override
//  public User login(User user) {
//    User loginUsers = null;
//
//    Session session = sessionFactory.getCurrentSession();
//
//    try {
//      session.beginTransaction();
//
//      loginUsers = (User) session.createQuery("FROM User WHERE username='" + user.getUsername()
//          + "' AND password='" + user.getPassword() + "'").getSingleResult();
//    } catch (NoResultException e) {
//      System.out.println("No result is found.");
//      return null;
//    } finally {
//      session.close();
//    }
//    return loginUsers;
//
//  }
  
  @Override
  public List<User> listUsers() {
  	Session session = sessionFactory.getCurrentSession();
  	List<User> resultList = null;
  	try  {
  		session.beginTransaction();
		resultList = session.createQuery("From User").getResultList();

  	}finally {
  		session.close();
  	}
	return resultList;
  }
  
  @Override
  public User getUserByUsername(String username) {
	  User savedUser = null;

      Session session = sessionFactory.getCurrentSession();
      try {
          session.beginTransaction();
          savedUser = (User) session.createQuery("FROM User u WHERE u.username =  '" + username + "'")
                  .getSingleResult();
      } finally {
          session.close();
      }
      return savedUser;
  }

}
