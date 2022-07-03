package com.example.backend.service;

import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import org.springframework.context.annotation.Bean;


public interface UserService {

    void registerUser(SignupRequest signupRequest);
    void loginUser(LoginRequest loginRequest);

}
