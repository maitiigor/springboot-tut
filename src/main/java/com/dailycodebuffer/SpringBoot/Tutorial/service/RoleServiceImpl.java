package com.dailycodebuffer.SpringBoot.Tutorial.service;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;


@Service
public class RoleServiceImpl implements RoleService{

    /* @Autowired
    private RoleRepository roleRepository; */

    @Override
    public Role deleteRole(Long id) {
       
        return null;
    }

    @Override
    public Role findRole(Long id) {
       
        return null;
    }

    @Override
    public Role saveDepartment(Role role) {
        
        return null;
    }

    @Override
    public Role updateRole(Role role, Long id) {
    
        return null;
    }

    @Override
    public void addRoleToUser(String email,String name) {
      
        
    }
    
}
