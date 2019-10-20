package com.ga.service;

import com.ga.entity.UserProfile;

public interface UserProfileService {
  public UserProfile getUserProfile(String username);
  public UserProfile createProfile(String username, UserProfile userProfile);
}
