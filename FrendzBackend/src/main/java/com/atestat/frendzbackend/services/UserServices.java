package com.atestat.frendzbackend.services;

import com.atestat.frendzbackend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

}
