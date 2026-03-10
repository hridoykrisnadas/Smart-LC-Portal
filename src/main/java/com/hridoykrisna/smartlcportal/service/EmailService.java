package com.hridoykrisna.smartlcportal.service;

public interface EmailService {
    void sendVerificationEmail(String toEmail, String token);
}
