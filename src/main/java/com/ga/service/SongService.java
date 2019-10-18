package com.ga.service;

import java.util.List;
import com.ga.entity.Song;

public interface SongService {
  public Song createSong(Song song);
  public Song deleteSong(Long songId);
  public List<Song> getAllSongs();
}
