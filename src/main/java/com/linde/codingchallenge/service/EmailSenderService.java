package com.linde.codingchallenge.service;

public interface EmailSenderService {

    void sendEmail(String toEmail, String eMailSubject, String body);

}
