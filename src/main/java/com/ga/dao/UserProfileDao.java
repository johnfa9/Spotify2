package com.ga.dao;

import com.ga.entity.UserProfile;

public interface UserProfileDao {
  public UserProfile createProfile(String username, UserProfile userProfile);
}
