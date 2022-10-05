package com.atestat.frendzbackend.controllers;

import com.atestat.frendzbackend.valueobjects.AccountUsernameDTO;
import com.atestat.frendzbackend.valueobjects.UserDTO;
import com.atestat.frendzbackend.entities.User;
import com.atestat.frendzbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<String> greetings() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.save(new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword()));
        return new ResponseEntity<>("User has been created!", HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<String> addingNewAccount(@RequestBody AccountUsernameDTO accountUsernameDTO) {
//        switch (accountUsernameDTO.getPlatform()) {
//            case "snapchat":
//                userService.saveSnapchatAccount(accountUsernameDTO.getUsername());
//                break;
//            case "facebook":
//                userService.saveFacebookAccount(accountUsernameDTO.getUsername());
//        }
//
//    }

}
