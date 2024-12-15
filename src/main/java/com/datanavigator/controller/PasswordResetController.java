package com.datanavigator.controller;

import com.datanavigator.service.PasswordResetService;
import com.datanavigator.repository.PasswordResetTokenRepository;
import com.datanavigator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @PostMapping("/reset")
    public String resetPassword(@RequestParam String email) {
        passwordResetService.resetPassword(email);
        return "Password reset email sent.";
    }

    @GetMapping("/reset-password")
    public String validateResetToken(@RequestParam String token) {
        String result = passwordResetService.validatePasswordResetToken(token);
        if ("valid".equals(result)) {
            return "Token is valid. You can now change your password.";
        }
        return "Invalid or expired token.";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String token, @RequestParam String password) {
        String result = passwordResetService.validatePasswordResetToken(token);
        if ("valid".equals(result)) {
            User user = passwordResetTokenRepository.findByToken(token).getUser();
            passwordResetService.changeUserPassword(user, password);
            return "Password changed successfully.";
        }
        return "Invalid or expired token.";
    }
}
