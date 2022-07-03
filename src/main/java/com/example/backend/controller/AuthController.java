package com.example.backend.controller;

import com.example.backend.jwt.JwtUtils;
import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.payload.response.JwtResponse;
import com.example.backend.payload.response.MessageResponse;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserDetailsImpl;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;


    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;




    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {

            return ResponseEntity.badRequest().body(new MessageResponse("Error: User name is already token"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {

            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use"));
        }

        userService.registerUser(signupRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
