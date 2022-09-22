package com.dailycodebuffer.SpringBoot.Tutorial.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Role;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.User;
import com.dailycodebuffer.SpringBoot.Tutorial.respository.UserRepository;
import com.dailycodebuffer.SpringBoot.Tutorial.respository.RoleRepository;


import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.notNull(fieldName);

        if (!fieldName.equals("email")) {
            throw new UnsupportedOperationException("Field name not supported");
        }

        if (value == null) {
            return false;
        }

        return userRepository.findByEmail(value.toString()).getEmail().isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email);
        if(user == null){
            log.info("User not found");
           
            throw new UsernameNotFoundException("User does not exist");

        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();

        user.getRoles().forEach(role ->{
            authority.add(new SimpleGrantedAuthority(role.getName()));
        });
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authority);
        return userDetails;
    }

    @Override
    public void deleteUserById(Long id) {
       
        userRepository.deleteById(id);
    }

    @Override
    public List<User> fetchUsers() {
       
        log.info("fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        log.info("fetching user by id");
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("updating user record");
        User updateUser = userRepository.findById(id).get();
    
        if(Objects.nonNull(updateUser.getEmail()) && !"".equalsIgnoreCase(updateUser.getEmail())){
            updateUser.setEmail(user.getEmail());
        }  
        if(Objects.nonNull(updateUser.getFirstName()) && !"".equalsIgnoreCase(updateUser.getFirstName())){
            updateUser.setFirstName(user.getFirstName());
        }
        if(Objects.nonNull(updateUser.getLastName()) && !"".equalsIgnoreCase(updateUser.getLastName())){
            updateUser.setLastName(user.getLastName());
        }

        return userRepository.save(updateUser);
    }

    @Override
    public User saveUser(User user) {
        log.info("saving user record");
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addUserRole(String email, String roleName) {
       
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);

    }

    @Override
    public void saveRole(Role role) {
     
        Role updRole = roleRepository.findByName(role.getName());

        if(updRole == null){
            roleRepository.save(role);
        }

    }

    @Override
    public User getUser(String username) {
     
        User user = userRepository.findByEmail(username);

       return user;

    }

    
}
