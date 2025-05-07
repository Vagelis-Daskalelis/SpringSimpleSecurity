package com.tasks.organizer.service;

import com.tasks.organizer.dao.request.SignUpRequest;
import com.tasks.organizer.dao.request.SigninRequest;
import com.tasks.organizer.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
