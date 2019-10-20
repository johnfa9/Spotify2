package com.ga.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "songs")
public class Song {

  @Id
  @Column(name = "song_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long songId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Integer length;

  
  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinTable(name = "user_song", joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = @JoinColumn(name = "song_id"))
  @JsonIgnore
  private List<User> userList;

  public Song() {}

  public Long getSongId() {
    return songId;
  }

  public void setSongId(Long songId) {
    this.songId = songId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }


}
