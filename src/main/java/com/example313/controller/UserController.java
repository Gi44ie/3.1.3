package com.example313.controller;

import com.example313.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @GetMapping("/user")
    public String getUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("authorizedUser", user);

        return "user/user";
    }
}