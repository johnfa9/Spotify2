package com.ga.service;

import com.ga.entity.UserProfile;

public class UserProfileServiceStub implements UserProfileService {

  @Override
  public UserProfile createProfile(String username, UserProfile userProfile) {

    UserProfile newProfile = new UserProfile();

    newProfile.setEmail("kyle@fake.com");

    return newProfile;
  }

  @Override
  public UserProfile getUserProfile(String username) {

    UserProfile userProfile = new UserProfile();

    userProfile.setEmail("kyle@fake.com");

    return userProfile;
  }
}
