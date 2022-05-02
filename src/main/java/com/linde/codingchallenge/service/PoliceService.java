package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Police;

import java.util.List;
import java.util.Optional;

public interface PoliceService {

    Police createPolice(Police police);

    Optional<Police> getPoliceById(Long id);

    List<Police> getAllPolices();

    Police updatePolice(Police police);

}
