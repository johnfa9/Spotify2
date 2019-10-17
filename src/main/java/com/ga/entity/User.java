package com.ga.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  
  @ManyToOne(cascade = {CascadeType.DETACH,
          CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_role_id", nullable = false)
	private UserRole userRole;
  
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

  public void setUsername(String username) {
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

  public UserRole getUserRole() {
	return userRole;
}

public void setUserRole(UserRole userRole) {
	this.userRole = userRole;
}
}
