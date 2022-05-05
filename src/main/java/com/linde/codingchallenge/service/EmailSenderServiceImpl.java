package com.linde.codingchallenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String eMailSubject, String body) {
        SimpleMailMessage messsage = new SimpleMailMessage();
        // TODO: Extract from env
        messsage.setFrom("codingchallengetesting@gmail.com");
        messsage.setTo(toEmail);
        messsage.setText(body);
        messsage.setSubject(eMailSubject);
        javaMailSender.send(messsage);
    }
}
