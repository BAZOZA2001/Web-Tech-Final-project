package com.datanavigator.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.datanavigator.model.User;  // Ensure this import is correct
import com.datanavigator.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String generateOtp(String username) {
        Random random = new Random();
        String otp = String.valueOf(100000 + random.nextInt(900000));
        otpStorage.put(username, otp);
        // Implement your email/SMS service here to send the OTP
        return otp;
    }

    public boolean verifyOtp(String username, String otp) {
        return otp.equals(otpStorage.get(username));
    }

    public String generateJwtToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS512, "SECRET_KEY")
            .compact();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void signUp(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        userRepository.save(user);
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Perform additional login logic here, if necessary.
            return true;
        }
        return false;
    }
}
