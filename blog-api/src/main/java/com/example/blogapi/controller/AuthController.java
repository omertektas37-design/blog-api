package com.example.blogapi.controller;

import com.example.blogapi.model.User;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername()).orElseThrow();
        if(dbUser.getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Hatalı giriş");
    }
}
