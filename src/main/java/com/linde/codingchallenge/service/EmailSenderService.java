package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.entity.Police;

import java.util.Optional;

public interface EmailSenderService {

    void sendEmail(Bike bike, Police police);

}
