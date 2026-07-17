package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationRequestDTO;
import com.example.demo.dto.AuthenticationResponseDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
    	user.setRole("ROLE_EMPLOYEE");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//        return jwtUtil.generateToken(userDetails);
        String jwt = jwtUtil.generateToken(userDetails);
        
        User user = repository.findByUsername(request.getUsername());
        
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt, userDetails.getUsername(), user.getEmail(), user.getRole()));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Secure World!";
    }
}
