package com.ga.dao;

import java.util.List;

import com.ga.entity.Song;
import com.ga.entity.User;

public class UserDaoStub implements UserDao {

  @Override
  public User signup(User user) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User login(User user) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String deleteUser(String username) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> listUsers() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getUserByUsername(String username) {
    User user = new User();
    user.setUsername(username);
    return user;
  }

  @Override
  public User addSongBySongId(String username, Long songId) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User deleteSongBySongId(String username, Long songId) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Song> listSongsByUser(String username) {

    // TODO Auto-generated method stub
    return null;
  }

}
