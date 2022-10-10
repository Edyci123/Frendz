package com.atestat.frendzbackend.controllers;

import com.atestat.frendzbackend.exceptions.PlatformNotExistentException;
import com.atestat.frendzbackend.services.UserService;
import com.atestat.frendzbackend.valueobjects.AddAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PatchMapping("/addAccount")
    public ResponseEntity<String> updateFacebookLink(@CurrentSecurityContext(expression = "authentication.principal") Principal principal, @RequestBody AddAccountRequest addAccountRequest) {
        try {
            System.out.println(principal.getName());
            Long id = Long.MIN_VALUE;
            userService.saveAccount(id, addAccountRequest);
            return ResponseEntity.ok().body("not ready yet");
        } catch (PlatformNotExistentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
