package com.ga.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  
  //added jfa 10 16
  @PreAuthorize("hasRole('ADMIN')")  //requests to /user should be limited to users with the ADMIN role
	@GetMapping("/list")    //change
	public List<User> listUsers() {
		return userService.listUsers();
	}
  //-----------------------------------

  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

  
// jfa remove 10 16  
//  @PostMapping("/signup")
//  public User signup(@RequestBody User user) {
//    return userService.signup(user);
//  }

  
  //jfa added 10 16
  @PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
      	return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
  	}
  //-----------------
  
  //jfa removed 10 16
  //@PostMapping("/login")
 // public User login(@RequestBody User user) {
 //   return userService.login(user);
  //}
  //--------------
  
  
  //jfa added 10 16
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user) {
      return ResponseEntity.ok(new JwtResponse(userService.login(user)));
  }
  //-------------
  
}
