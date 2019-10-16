package com.ga.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  
  @Column(name="username", unique=true, nullable=false)
  private String username;
  
  @Column(name="password", nullable=false)
  private String password;
  
//  private List<Song> songList;

  public User(){}
  
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public List<Song> getSongList() {
//    return songList;
//  }
//
//  public void setSongList(List<Song> songList) {
//    this.songList = songList;
//  }

}
