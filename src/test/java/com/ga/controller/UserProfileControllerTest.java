package com.ga.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ga.entity.UserProfile;
import com.ga.service.UserProfileServiceStub;

public class UserProfileControllerTest {

  private UserProfileController userProfileController;

  private UserProfile userProfile;

  @Before
  public void initializeUserProfileController() {

    userProfileController = new UserProfileController();
    userProfileController.setUserProfileService(new UserProfileServiceStub());
  }

  @Before
  public void initializeUserProfile() {

    userProfile = new UserProfile();
    userProfile.setProfileId(1L);
    userProfile.setEmail("kyle@fake.com");
    userProfile.setAddress("nyc");
    userProfile.setMobile("phone");
  }

  @Test
  public void createUserProfile_SaveUserProfile_Success() {

    UserProfile userProfile = new UserProfile();

    userProfile.setEmail("kyle@fake.com");

    UserProfile newProfile = userProfileController.createUserProfile("kyle", userProfile);

    assertNotNull(newProfile);
    assertEquals(userProfile.getEmail(), newProfile.getEmail());
  }

  @Test
  public void getUserProfile_UserProfile_Success() {

    UserProfile newProfile = userProfileController.getUserProfile("kyle");

    assertNotNull(newProfile);
    assertEquals(newProfile.getEmail(), userProfile.getEmail());
  }

}
