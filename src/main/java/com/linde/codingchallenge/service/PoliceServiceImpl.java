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
    public Optional<Police> findPoliceById(Long id) {
        return policeRepository.findById(id);
    }

    @Override
    public List<Police> findAllPolice() {
        return policeRepository.findAll();
    }

    @Override
    public List<Police> findAllPoliceInvestigating() {
        return policeRepository.findAllPoliceByInvestigatingTrue();
    }

    @Override
    public List<Police> findAllPoliceNotInvestigating() {
        return policeRepository.findAllPoliceByInvestigatingFalse();
    }

}
