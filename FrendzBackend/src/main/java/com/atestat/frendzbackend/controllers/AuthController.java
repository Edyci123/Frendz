package com.atestat.frendzbackend.controllers;

import com.atestat.frendzbackend.entities.User;
import com.atestat.frendzbackend.repos.UserRepository;
import com.atestat.frendzbackend.services.UserService;
import com.atestat.frendzbackend.utils.AuthRequest;
import com.atestat.frendzbackend.utils.AuthResponse;
import com.atestat.frendzbackend.utils.JwtTokenUtil;
import com.atestat.frendzbackend.valueobjects.UserDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            User user = (User) auth.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse authResponse = new AuthResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(authResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.save(new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword()));
        return ResponseEntity.ok().body("User created");
    }

}
