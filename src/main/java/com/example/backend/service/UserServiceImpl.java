package com.example.backend.service;

import com.example.backend.jwt.JwtUtils;
import com.example.backend.model.Role;
import com.example.backend.model.RoleEnum;
import com.example.backend.model.User;
import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void registerUser(SignupRequest signupRequest) {

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {

            Role userRole = roleRepository.findByName((RoleEnum.ROLE_USER)).orElseThrow( () -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

        }else {

            strRoles.forEach(role -> {

                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow( ()-> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;

                    case "mod":
                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;

                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);


    }

    @Override
    public void loginUser(LoginRequest loginRequest) {



    }
}
