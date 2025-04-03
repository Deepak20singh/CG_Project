package com.example.LoginSignup.service;

import com.example.LoginSignup.component.JwtUtil;
import com.example.LoginSignup.dto.UserDTO;
import com.example.LoginSignup.model.UserModel;
import com.example.LoginSignup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signup(UserDTO dto) {
        Optional<UserModel> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Signup Failed: Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        UserModel model = new UserModel(
                dto.getId(),
                dto.getUserName() != null ? dto.getUserName() : "DefaultUser",
                dto.getEmail(),
                dto.getRole() != null ? dto.getRole() : "USER",
                encodedPassword
        );

        userRepository.save(model);
        return ResponseEntity.ok("Signup Successful: Welcome " + model.getUserName() + "!");
    }

    public ResponseEntity<String> login(String email, String password) {
        Optional<UserModel> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Login Failed: User not found");
        }

        UserModel user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.badRequest().body("Login Failed: Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok("Login Successful: " + token);
    }
}
