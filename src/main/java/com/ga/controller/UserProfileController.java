package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ga.entity.UserProfile;
import com.ga.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
  
  private UserProfileService userProfileService;
  
  @Autowired
  public void setUserProfileService(UserProfileService userProfileService) {
      this.userProfileService = userProfileService;
  }
  
  @PreAuthorize("#username == authentication.name")
  @PostMapping("/{username}")
  public UserProfile createUserProfile(@PathVariable("username") String username, @RequestBody UserProfile userProfile) {
      return userProfileService.createProfile(username, userProfile);
  }
  
  @PreAuthorize("#username == authentication.name")
  @GetMapping("/profile/{username}")
  public UserProfile getUserProfile(@PathVariable("username") String username){
    return userProfileService.getUserProfile(username);
  }
}
