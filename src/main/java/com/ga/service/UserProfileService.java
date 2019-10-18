package com.ga.service;

import com.ga.entity.UserProfile;

public interface UserProfileService {
  public UserProfile createProfile(String username, UserProfile userProfile);
}
