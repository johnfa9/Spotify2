package com.ga.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ga.entity.Song;

@Repository
public class SongDaoImpl implements SongDao {

  @Autowired
  SessionFactory sessionFactory;

  @Override
  public Song createSong(Song song) {
    
    try(Session session = sessionFactory.getCurrentSession();) {
      session.beginTransaction();
      session.save(song);
      session.getTransaction().commit();
    }
    return song;
  }

  @Override
  public Song deleteSong(Long songId) {
    Song song = null;
    try(Session session = sessionFactory.getCurrentSession();) {
      session.beginTransaction();
      song = session.get(Song.class, songId);
      session.delete(song);
      session.getTransaction().commit();
    }
    return song;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Song> getAllSongs() {
   
    List<Song> songList = null;
    try(Session session = sessionFactory.getCurrentSession();) {
      session.beginTransaction();
      songList = session.createQuery("From Song").getResultList();
    }
    return songList;
  }

}
