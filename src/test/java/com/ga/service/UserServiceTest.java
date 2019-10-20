package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.entity.UserRole;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

  @Mock
  UserDao userDao;

  @Mock
  private JwtUtil jwtUtil;

  @Mock
  private PasswordEncoder bCryptPasswordEncoder;

  @InjectMocks
  private UserServiceImpl userService;

  @InjectMocks
  private User user;

  @InjectMocks
  private UserRole userRole;

  @InjectMocks
  private Song song;

  private List<User> userList;
  private List<Song> songList;

  @Before
  public void initMocks() {

    MockitoAnnotations.initMocks(this);
  }

  @Before
  public void initializeDummyUser() {

    userList = new ArrayList<User>();
    songList = new ArrayList<Song>();

    userRole.setName("ROLE_ADMIN");

    user.setUserId(1L);
    user.setUsername("batman");
    user.setPassword("robin");
    user.setUserRole(userRole);

    song.setSongId(1L);
    song.setTitle("song1");
    song.setLength(1);

    songList.add(song);
    user.setSongList(songList);
    userList.add(user);

  }

  @Test
  public void signup_ReturnsJwt_Success() {

    String expectedToken = "12345";

    userRole.setName("ROLE_ADMIN");

    user.setUserId(1L);
    user.setUsername("batman");
    user.setPassword("robin");
    user.setUserRole(userRole);

    when(userDao.signup(any())).thenReturn(user);
    when(userDao.getUserByUsername(anyString())).thenReturn(user);
    when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
    when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");

    String actualToken = userService.signup(user);

    assertEquals(expectedToken, actualToken);
  }

  @Test
  public void signup_UserNotFound_Error() {

    user.setUserId(null);
    user.setUsername("batman");
    user.setPassword("robin");

    when(userDao.signup(any())).thenReturn(user);

    String token = userService.signup(user);

    assertEquals(null, token);
  }

  @Test
  public void listUsers_returnUserList_Success() {

    when(userDao.listUsers()).thenReturn(userList);

    List<User> actualUserList = userService.listUsers();

    assertEquals(userList.get(0), actualUserList.get(0));
  }

  @Test
  public void addSongBySongId_returnUserWithSong_Success() {

    when(userDao.addSongBySongId(anyString(), anyLong())).thenReturn(user);

    User actualUser = userService.addSongBySongId("batman", 1L);

    assertEquals(user, actualUser);
  }

  @Test
  public void loadUserByUsername_UserDetails_Success() {

    when(userDao.getUserByUsername(anyString())).thenReturn(user);
    when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");

    UserDetails userDetails = userService.loadUserByUsername("batman");

    assertEquals(user.getUsername(), userDetails.getUsername());
  }

  @Test(expected = UsernameNotFoundException.class)
  public void loadUserByUsername_UserDetails_UserNotFound() {

    when(userDao.getUserByUsername(anyString())).thenReturn(null);

    userService.loadUserByUsername("batman");
  }

  @Test
  public void deleteUser_Username_Success() {

    when(userDao.deleteUser(anyString())).thenReturn("batman");

    String actualTarget = userService.deleteUser("batman");

    assertEquals("batman", actualTarget);
  }

  @Test
  public void deleteUser_Username_UserNotFound() {

    when(userDao.deleteUser(anyString())).thenReturn(null);

    String actualTarget = userService.deleteUser("batman");

    assertEquals(null, actualTarget);
  }

  @Test
  public void deleteSongBySongId_UserWithFewerSong_Success() {

    user.setSongList(new ArrayList<Song>());

    when(userDao.deleteSongBySongId(anyString(), anyLong())).thenReturn(user);

    User user = userService.deleteSongBySongId("batman", 1L);
    assertEquals(0, user.getSongList().size());
  }

  @Test
  public void deleteSongBySongId_UserWithFewerSong_UserNotFound() {

    when(userDao.deleteSongBySongId(anyString(), anyLong())).thenReturn(null);

    User user = userService.deleteSongBySongId("batman", 1L);
    assertEquals(null, user);
  }

  @Test
  public void listSongs_ReturnSongList_Success() {

    when(userDao.listSongsByUser(anyString())).thenReturn(songList);

    List<Song> actualSongList = userService.listSongsByUser("batman");
    assertEquals(songList, actualSongList);
    assertEquals(songList.size(), actualSongList.size());
    assertEquals(songList.get(0).getSongId(), actualSongList.get(0).getSongId());

  }

}
