package com.ga.dao;

import java.util.List;
import com.ga.entity.Song;

public interface SongDao {
  public Song createSong(Song song);
  public Song deleteSong(Long songId);
  public List<Song> getAllSongs();
}
