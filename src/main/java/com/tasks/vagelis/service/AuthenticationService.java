package com.tasks.vagelis.service;

import com.tasks.vagelis.dao.request.SignUpRequest;
import com.tasks.vagelis.dao.request.SigninRequest;
import com.tasks.vagelis.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
