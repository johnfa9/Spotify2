package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ga.entity.UserProfile;
import com.ga.entity.UserRole;
import com.ga.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
  @Autowired
  UserProfileService userProfileService;
  
  @PostMapping("/{username}")
  public UserProfile createUserProfile(@PathVariable String username, @RequestBody UserProfile userProfile) {
      return userProfileService.createProfile(username, userProfile);
  }
}
