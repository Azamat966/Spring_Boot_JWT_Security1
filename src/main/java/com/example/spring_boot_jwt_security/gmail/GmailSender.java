package com.example.spring_boot_jwt_security.gmail;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Service
    public class GmailSender {
    private final JavaMailSender javaMailSender;
        public void emailSender(String email){
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            Random random = new Random();
            try {
                helper.setTo(email);
                helper.setSubject("Ваш код");
                random.nextInt(1,10);
                random.nextInt(1,10);
                random.nextInt(1,10);
                random.nextInt(1,10);
                random.nextInt(1,10);
                random.nextInt(1,10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            javaMailSender.send(message);
        }
    }
