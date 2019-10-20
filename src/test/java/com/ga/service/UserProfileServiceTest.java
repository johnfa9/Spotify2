package com.ga.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ga.dao.UserDaoStub;
import com.ga.dao.UserProfileDaoStub;
import com.ga.entity.UserProfile;

public class UserProfileServiceTest {

  private UserProfileServiceImpl userProfileService;
  private UserProfile userProfile;

  @Before
  public void initializeUserProfile() {

    userProfileService = new UserProfileServiceImpl(new UserDaoStub(), new UserProfileDaoStub());
    userProfile = new UserProfile();

    userProfile.setProfileId(1L);
    userProfile.setEmail("kyle@fake.com");
    userProfile.setAddress("nyc");
    userProfile.setMobile("phone");
  }

  @Test
  public void createUserProfile_AddsProfile_Success() {

    UserProfile userProfile = new UserProfile();

    userProfile.setEmail("kyle@fake.com");

    UserProfile newProfile = userProfileService.createProfile("kyle", userProfile);

    assertNotNull(newProfile);
    assertEquals(userProfile.getEmail(), newProfile.getEmail());
  }

  @Test
  public void getUserProfile_AddsProfile_Success() {

    UserProfile newProfile = userProfileService.getUserProfile("kyle");

    assertNotNull(newProfile);
    assertEquals(userProfile.getEmail(), newProfile.getEmail());
  }
}
