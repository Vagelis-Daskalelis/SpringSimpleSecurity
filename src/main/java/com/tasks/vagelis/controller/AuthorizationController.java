package com.tasks.vagelis.controller;

import com.tasks.vagelis.dao.request.UserUpdateRequest;
import com.tasks.vagelis.entities.User;
import com.tasks.vagelis.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationServiceImpl authenticationService;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Here is your resource");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("Here is your resource user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Here is your resource admin");
    }


    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody UserUpdateRequest request,
                                           Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal(); // you can cast safely now
        Long currentUserId = currentUser.getId();

        User updatedUser = authenticationService.updateUser(request, id);
        return ResponseEntity.ok(updatedUser);
    }
}
