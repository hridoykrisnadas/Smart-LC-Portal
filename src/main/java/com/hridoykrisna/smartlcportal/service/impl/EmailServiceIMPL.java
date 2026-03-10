package com.hridoykrisna.smartlcportal.service.impl;

import com.hridoykrisna.smartlcportal.repository.VerificationTokenRepo;
import com.hridoykrisna.smartlcportal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceIMPL implements EmailService {

    @Value("${app.base-url}")
    private String baseURL;

    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String toEmail, String token) {
        String verificationURL = baseURL + "api/auth/verification?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Smart-LC-Portal - Complete Your Registration");
        message.setText("Welcome to Smart LC Portal!\n\nPlease click the link below to verify your account and complete your registration:\n" + verificationURL);

        mailSender.send(message);
    }
}
