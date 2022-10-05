package com.atestat.frendzbackend.services;

import com.atestat.frendzbackend.entities.User;
import com.atestat.frendzbackend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

//    public void saveSnapchatAccount(String username) {
//
//    }
//
//    public void saveFacebookAccount(String username) {
//    }
}
