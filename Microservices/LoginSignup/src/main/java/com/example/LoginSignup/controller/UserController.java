package com.example.LoginSignup.controller;

import com.example.LoginSignup.dto.UserDTO;
import com.example.LoginSignup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO dto) {

        return userService.signup(dto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO dto) {
        return userService.login(dto.getEmail(), dto.getPassword());
    }
}
