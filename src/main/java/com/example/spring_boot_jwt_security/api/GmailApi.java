package com.example.spring_boot_jwt_security.api;
import com.example.spring_boot_jwt_security.gmail.GmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

   @RestController
   @RequestMapping("/api/email")
//   @Api(tags = "Email API", description = "API для отправки электронных писем")
    public class GmailApi {
    private final GmailSender sender;

    @Autowired
    public GmailApi(GmailSender sender) {
        this.sender = sender;
    }

    @GetMapping("/send")
    public String sendEmail(@RequestParam String email) {
        // Генерируем случайный шестизначный код
        String verificationCode = generateSixDigitCode();

        // Отправляем шестизначный код на указанный email
        sender.sendVerificationCode(email, verificationCode);

        return "successfully sent ✅";
    }

    // Метод для генерации случайного шестизначного кода
    private String generateSixDigitCode() {
        Random random = new Random();
        int min = 100000; // Минимальное значение для шестизначного числа
        int max = 999999; // Максимальное значение для шестизначного числа
        int randomCode = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomCode);
    }
}