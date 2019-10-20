package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  UserController userController;

  List<User> userList;
  List<Song> songList;

  @InjectMocks
  User user1;

  @InjectMocks
  Song song1;
  
  @Before
  public void init() {

    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Before
  public void setup() {

    userList = new ArrayList<User>();
    songList = new ArrayList<Song>();

    user1.setUserId(1L);
    user1.setUsername("kyle");
    user1.setPassword("kyle");

    song1.setSongId(1L);
    song1.setTitle("song1");
    song1.setLength(1);

    songList.add(song1);
    user1.setSongList(songList);
    userList.add(user1);
  }

  @Test
  public void signup_User_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/signup")
        .contentType(MediaType.APPLICATION_JSON).content(createUserInJson("joe", "abc"));

    when(userService.signup(any())).thenReturn("123456");

    MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
        .andExpect(content().json("{\"token\":\"123456\"}")).andReturn();

    System.out.println(result.getResponse().getContentAsString());
  }

  @Test
  public void login_User_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login")
        .contentType(MediaType.APPLICATION_JSON).content(createUserInJson("joe", "abc"));

    when(userService.login(any())).thenReturn("123456");

    mockMvc.perform(requestBuilder).andExpect(status().isOk())
        .andExpect(content().json("{\"token\":\"123456\"}"));
  }

  @Test
  public void deleteUserByUserName_User_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/delete/kyle");

    when(userService.deleteUser(anyString())).thenReturn("kyle");

    mockMvc.perform(requestBuilder).andExpect(status().isOk())
        .andExpect(content().string("deleted: kyle"));
  }

  @Test
  public void listUsers_UserList_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/list");

    when(userService.listUsers()).thenReturn(userList);

    mockMvc.perform(requestBuilder).andExpect(status().isOk());

    assertEquals("kyle", user1.getUsername());
  }

  @Test
  public void addSongToUser_UserWithSongList_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/kyle/song/add/1");

    when(userService.addSongBySongId(anyString(), anyLong())).thenReturn(user1);

    mockMvc.perform(requestBuilder).andExpect(status().isOk());

    assertEquals("kyle", user1.getUsername());
    assertEquals("song1", user1.getSongList().get(0).getTitle());
  }

  @Test
  public void deleteSongToUser_UserWithSongList_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/kyle/song/delete/1");

    when(userService.deleteSongBySongId(anyString(), anyLong())).thenReturn(user1);

    mockMvc.perform(requestBuilder).andExpect(status().isOk());

    user1.deleteSong(song1);
    assertEquals(0, user1.getSongList().size());
  }

  @Test
  public void listSongsByUsername_SongList_Success() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/songlist/kyle");

    when(userService.listSongsByUser(anyString())).thenReturn(user1.getSongList());

    mockMvc.perform(requestBuilder).andExpect(status().isOk());

    assertEquals(1, user1.getSongList().size());
  }

  private static String createUserInJson(String username, String password) {

    return "{ \"username\": \"" + username + "\", " + "\"password\":\"" + password + "\"}";
  }
}
