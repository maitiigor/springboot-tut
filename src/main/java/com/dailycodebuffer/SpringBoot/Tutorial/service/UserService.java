package com.dailycodebuffer.SpringBoot.Tutorial.service;

import java.util.List;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.User;
import com.dailycodebuffer.SpringBoot.Tutorial.validator.FieldValueExists;

public interface UserService extends FieldValueExists {
    
    public User saveUser(User user);

    public List<User> fetchUsers();

    public void addUserRole(String email,String roleName);

    public User findUserById(Long id) ;

    public void deleteUserById(Long departmentId);

    public User updateUser(Long departmentId, User user);

    public void saveRole(Role name);

    public User getUser(String username);
   /*  public User fetchDeparmentByName(String name); */
}
