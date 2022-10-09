package com.atestat.frendzbackend.controllers;


import com.atestat.frendzbackend.services.UserService;
import com.atestat.frendzbackend.valueobjects.AccountUsernameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
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

    @PatchMapping
    public ResponseEntity<String> updateFacebookLink(@CurrentSecurityContext(expression = "authentication.principal")Principal principal, @RequestBody AccountUsernameDTO usernameDTO) {
        //System.out.println(principal.getName());
        return ResponseEntity.ok().body("Gotta do the frontend!");
    }

}
