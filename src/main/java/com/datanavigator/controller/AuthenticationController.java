package com.datanavigator.controller;

import com.datanavigator.model.User;  // Ensure this import is correct
import com.datanavigator.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate OTP and send via email/SMS
        String otp = userService.generateOtp(loginRequest.getUsername());
        // Assuming you have a service to send OTP

        return "OTP sent to your email/SMS";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String username, @RequestParam String otp) {
        if (userService.verifyOtp(username, otp)) {
            // Issue JWT token
            String token = userService.generateJwtToken(username);
            return "JWT token: " + token;
        }
        return "Invalid OTP";
    }
}
