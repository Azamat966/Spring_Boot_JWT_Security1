package com.example.spring_boot_jwt_security.gmail;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service

    public class GmailSender {
        private final JavaMailSender sender;

    public void sendVerificationCode(String email, String code) {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            try {
                helper.setTo(email);
                helper.setSubject("Verification Code");
                helper.setText("Your verification code is: " + code);
            } catch (Exception e) {
                e.printStackTrace();
            }

            sender.send(message);
        }
    }
