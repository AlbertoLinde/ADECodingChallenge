package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Bike bike) {
        buildMessage(bike);
    }

    private void buildMessage(Bike bike) {
        SimpleMailMessage email = new SimpleMailMessage();
        String senderEmail = "codingchallengetesting@gmail.com";

        email.setFrom(senderEmail);
        email.setTo(bike.getEmail());
        email.setText("Hello " + bike.getOwnerName() + " , your bike with the licence number "
                + bike.getLicenceNumber() + " was found by " + bike.getPolice().getName() + ". " +
                "Please, come to the office station.");
        email.setSubject("Hello, " + bike.getOwnerName() + ". Found the bike: " + bike.getLicenceNumber());

        javaMailSender.send(email);
    }


}
