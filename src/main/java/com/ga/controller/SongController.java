package com.ga.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ga.entity.Song;
import com.ga.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
  @Autowired
  SongService songService;

  @PostMapping("/create")
  public Song createSong(@RequestBody Song song) {
    return songService.createSong(song);
  }
  
  @DeleteMapping("/delete/{songId}")
  public Song deleteSongById(@PathVariable Long songId){
    return songService.deleteSong(songId);
  }
  
  @GetMapping("")
  public List<Song> getAllSongs(){
    return songService.getAllSongs();
  }

}
