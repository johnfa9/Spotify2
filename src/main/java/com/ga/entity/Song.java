package com.ga.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {

  @Id
  @Column(name = "song_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long songId;

  @Column(nullable=false)
  private String title;
  
  @Column(nullable=false)
  private Integer length;
  
//  private List<User> subscriberList;

  public Song(){}
  
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

//  public List<User> getSubscriberList() {
//    return subscriberList;
//  }
//
//  public void setSubscriberList(List<User> subscriberList) {
//    this.subscriberList = subscriberList;
//  }


}
