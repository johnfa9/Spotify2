package com.ga.service;

import java.util.List;

import com.ga.entity.UserRole;

public interface UserRoleService {

  public UserRole createRole(UserRole newRole);

  public UserRole getRole(String roleName);
  
  public List<UserRole> listAllRoles();

}
