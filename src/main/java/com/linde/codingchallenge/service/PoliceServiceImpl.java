package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    private PoliceRepository policeRepository;

    @Override
    public Police createPolice(Police police) {
        return policeRepository.save(police);
    }

    @Override
    public Police updatePolice(Police police) {
        return policeRepository.save(police);
    }

    @Override
    public Optional<Police> getPoliceById(Long id) {
        return policeRepository.findById(id);
    }

    @Override
    public List<Police> getAllPolices() {
        return policeRepository.findAll();
    }

    public List<Police> getAllPolicesInvestigating() {
        return policeRepository.findAllPoliceByInvestigatingTrue();
    }

    public List<Police> getAllPolicesNotInvestigating() {
        return policeRepository.findAllPoliceByInvestigatingFalse();
    }

}
