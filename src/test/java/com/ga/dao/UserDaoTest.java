package com.ga.dao;

import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.entity.UserRole;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @InjectMocks
  private User user;
  
  @InjectMocks
  private Song song;

  @InjectMocks
  private UserRole userRole;

  @InjectMocks
  private UserDaoImpl userDao;

  @Mock
  private UserRoleDao userRoleDao;

  @Mock
  private SessionFactory sessionFactory;

  @Mock
  private Session session;

  @Mock
  private Transaction transaction;

  @Mock
  private Query<User> query;

  private List<User> userList;

  @Before
  public void init() {

    userList = new ArrayList<User>();

    when(sessionFactory.getCurrentSession()).thenReturn(session);
    when(session.getTransaction()).thenReturn(transaction);

    userRole.setRoleId(1);
    userRole.setName("ROLE_ADMIN");

    user.setUserId(1L);
    user.setUsername("batman");
    user.setPassword("robin");
    user.setUserRole(userRole);
    
    song.setSongId(1L);
    song.setTitle("Song1");
    song.setLength(1);
    user.addSong(song);
    
    userList.add(user);
  }

  @Test
  public void signup_User_Success() {

    when(userRoleDao.getRole(anyString())).thenReturn(userRole);
    User savedUser = userDao.signup(user);

    assertNotNull("Test returned null object, expected non-null", savedUser);
    assertEquals(savedUser, user);
  }

  @Test
  public void login_User_Success() {

    when(session.createQuery(anyString())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(user);
    User savedUser = userDao.login(user);

    assertNotNull("Test returned null object, expected non-null", savedUser);
    assertEquals(savedUser, user);
  }

  @Test
  public void listUsers_UserList_Success() {

    when(session.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(userList);
    List<User> actualUserList = userDao.listUsers();

    assertEquals(userList, actualUserList);
  }

  @Test
  public void getUserByUsername_User_Success() {

    when(session.createQuery(anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(user);

    User actualUser = userDao.getUserByUsername("batman");
    assertEquals(user.getUserId(), actualUser.getUserId());
  }

  @Test
  public void getUserByUsername_Null_UserNotFound() {

    when(session.createQuery(anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(null);

    User actualUser = userDao.getUserByUsername("batman");
    assertEquals(null, actualUser);
  }
  
  @Test
  public void addSongBySongId_UserWithSongList_Success(){
    when(session.createQuery(anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(user);
    
    User actualUser = userDao.addSongBySongId("batman", 1L);
    assertEquals(user.getSongList().size(), actualUser.getSongList().size());
  }
  
  @Test
  public void listSongsByUser_SongList_Success(){
    when(session.createQuery(anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(user);
    
    List<Song> actualSongList = userDao.listSongsByUser("batman");
    
    assertEquals(user.getSongList(), actualSongList);
    assertEquals(user.getSongList().size(), actualSongList.size());
    assertEquals(user.getSongList().get(0).getSongId(), actualSongList.get(0).getSongId());
  }
}
