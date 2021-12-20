package com.example313.controller;

import com.example313.model.User;
import com.example313.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        return "admin/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(required = false, name = "newRoles") String[] newRoles) {

        if (bindingResult.hasErrors()) {
            return "admin/new";
        }

        userService.addUser(user, newRoles);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/admin";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(required = false, name = "currentRoles") String[] currentRoles) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        userService.updateUser(user, currentRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}