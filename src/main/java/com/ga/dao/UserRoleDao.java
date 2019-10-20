package com.ga.dao;

import java.util.List;

import com.ga.entity.UserRole;

public interface UserRoleDao {

  public UserRole createRole(UserRole newRole);

  public UserRole getRole(String roleName);
  
  public List<UserRole> listAllRoles();

}
