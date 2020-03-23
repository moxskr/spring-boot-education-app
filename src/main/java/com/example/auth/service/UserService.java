package com.example.auth.service;

import com.example.auth.entities.Role;
import com.example.auth.entities.User;
import com.example.auth.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepo.findByUsername(username);
    }

    public User save(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        user.encode();
        return userRepo.save(user);
    }

    public boolean ifUserAlreadyExists(User user) {
        User user1 = userRepo.findByUsername(user.getUsername());
        User user2 = userRepo.findByEmail(user.getEmail());
        if(user1 != null || user2 != null) {
            return true;
        }
        return false;
    }
}
