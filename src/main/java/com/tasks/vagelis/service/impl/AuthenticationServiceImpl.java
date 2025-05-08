package com.tasks.vagelis.service.impl;

import com.tasks.vagelis.dao.request.SignUpRequest;
import com.tasks.vagelis.dao.request.SigninRequest;
import com.tasks.vagelis.dao.response.JwtAuthenticationResponse;
import com.tasks.vagelis.entities.Role;
import com.tasks.vagelis.entities.User;
import com.tasks.vagelis.repository.UserRepository;
import com.tasks.vagelis.service.AuthenticationService;
import com.tasks.vagelis.service.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);
        if (adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
            System.out.println("Admin created successfully");
        }else {
            System.out.println("Admin already exists");
        }
    }
}
