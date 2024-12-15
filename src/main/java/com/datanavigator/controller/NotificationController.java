package com.datanavigator.controller;

import com.datanavigator.service.NotificationService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        try {
            notificationService.sendEmail(to, subject, text);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email.";
        }
    }

    @GetMapping("/send-alert")
    public String sendAlert(@RequestParam String message) {
        notificationService.sendAlert(message);
        return "Alert sent successfully!";
    }
}
