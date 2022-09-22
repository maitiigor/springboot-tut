package com.dailycodebuffer.SpringBoot.Tutorial.service;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;

public interface RoleService {

    public Role deleteRole(Long id);
    public Role findRole(Long id);
    public Role updateRole(Role role,Long id);
    public Role saveDepartment(Role role);
    void addRoleToUser(String email,String name);
}
