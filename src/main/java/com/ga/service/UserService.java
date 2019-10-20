package com.ga.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.Song;
import com.ga.entity.User;

public interface UserService extends UserDetailsService {
  public String signup(User user);
  public String login(User user);
  public String deleteUser(String username);
  public List<User> listUsers();

  public User addSongBySongId(String username, Long songId);
  public User deleteSongBySongId(String username, Long songId);
  public List<Song> listSongsByUser(String username);

}
