package com.tasks.vagelis.service;

import com.tasks.vagelis.dao.request.SignUpRequest;
import com.tasks.vagelis.dao.request.SigninRequest;
import com.tasks.vagelis.dao.request.UserUpdateRequest;
import com.tasks.vagelis.dao.response.JwtAuthenticationResponse;
import com.tasks.vagelis.entities.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    User updateUser(UserUpdateRequest request, Long targetUserId, Long currentUserId);
}
