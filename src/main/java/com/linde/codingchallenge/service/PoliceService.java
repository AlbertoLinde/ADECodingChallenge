package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Police;

import java.util.List;
import java.util.Optional;

public interface PoliceService {

    Police createPolice(Police police);

    Optional<Police> findPoliceById(Long id);

    List<Police> findAllPolice();

    Police updatePolice(Police police);

    List<Police> findAllPoliceInvestigating();

    List<Police> findAllPoliceNotInvestigating();

}
