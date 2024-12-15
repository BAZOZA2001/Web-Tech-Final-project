package com.datanavigator.controller;

import com.datanavigator.model.User;  // Ensure this import is correct
import com.datanavigator.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        userService.signUp(username, password, email);
        return "Sign up successful. You can now login.";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (userService.login(username, password)) {
            return "Login successful.";
        }
        return "Invalid credentials.";
    }
}
