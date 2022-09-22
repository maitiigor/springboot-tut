package com.dailycodebuffer.SpringBoot.Tutorial.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName (String name);
}
