package com.ga.dao;

import java.util.List;

import com.ga.entity.Song;
import com.ga.entity.User;

public interface UserDao {
  public User signup(User user);
  public User login(User user);
  public String deleteUser(String username);
  public List<User> listUsers();
  public User getUserByUsername(String username);
  
  public User addSongBySongId(String username, Long songId);
  public User deleteSongBySongId(String username, Long songId);
  public List<Song> listSongsByUser(String username);
}
