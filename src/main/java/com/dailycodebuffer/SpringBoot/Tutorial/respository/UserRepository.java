package com.dailycodebuffer.SpringBoot.Tutorial.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    
}

