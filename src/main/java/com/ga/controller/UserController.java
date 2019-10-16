package com.ga.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

  @PostMapping("/signup")
  public User signup(@RequestBody User user) {
    return userService.signup(user);
  }

  @PostMapping("/login")
  public User login(@RequestBody User user) {
    return userService.login(user);
  }
}
