package com.atestat.frendzbackend.services;

import com.atestat.frendzbackend.entities.User;
import com.atestat.frendzbackend.exceptions.PlatformNotExistentException;
import com.atestat.frendzbackend.repos.UserRepository;
import com.atestat.frendzbackend.valueobjects.AddAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public void saveAccount(Long Id, AddAccountRequest addAccountRequest) throws PlatformNotExistentException {
        switch (addAccountRequest.getPlatform()) {
            case "facebook":
                userRepository.updateFacebookLink(Id, addAccountRequest.getUsername());
                break;
            case "instagram":
                userRepository.updateInstagramLink(Id, addAccountRequest.getUsername());
                break;
            default:
                throw new PlatformNotExistentException("This platform isn't available");
        }
    }
}
