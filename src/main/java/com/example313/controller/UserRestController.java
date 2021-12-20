package com.example313.controller;

import com.example313.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserRestController {

    @GetMapping("/authorizedUser")
    public ResponseEntity<User> getUserPage(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
    }
}