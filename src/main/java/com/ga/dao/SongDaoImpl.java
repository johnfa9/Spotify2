package com.ga.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ga.entity.Song;
import com.ga.entity.User;

@Repository
public class SongDaoImpl implements SongDao {

  @Autowired
  SessionFactory sessionFactory;

  @Override
  public Song createSong(Song song) {
    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();
      session.save(song);
      session.getTransaction().commit();
    } finally {
      session.close();
    }
    return song;
  }

  @Override
  public Song deleteSong(Long songId) {
    Session session = sessionFactory.getCurrentSession();
    Song song = null;
    try {
      session.beginTransaction();
      song = session.get(Song.class, songId);
      session.delete(song);
      session.getTransaction().commit();
    } finally {
      session.close();
    }
    return song;
  }

  @Override
  public List<Song> getAllSongs() {
    Session session = sessionFactory.getCurrentSession();
    List<Song> songList = null;
    try {
      session.beginTransaction();
      songList = session.createQuery("From Song").getResultList();
    } finally {
      session.close();
    }
    return songList;
  }

}
