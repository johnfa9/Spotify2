package com.ga.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.ga.entity.User;

public interface UserService extends UserDetailsService {
  public String signup(User user);
  public String login(User user);
  public List<User> listUsers();
  
  public User addSong(String username, Long songId);
}
