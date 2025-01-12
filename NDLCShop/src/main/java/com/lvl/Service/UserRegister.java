package com.lvl.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvl.Entity.AppRole;
import com.lvl.Entity.User;
import com.lvl.Repository.AppRoleRepository;
import com.lvl.Repository.UserRepository;

@Service
public class UserRegister {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AppRoleRepository appRoleRepository;


    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public AppRole getRoleById(Integer roleId) {
        return appRoleRepository.findById(1);
    }
}
