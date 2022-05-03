package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.repository.BikeRepository;
import com.linde.codingchallenge.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {


    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private PoliceRepository policeRepository;

    @Override
    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Optional<Bike> getBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    public Optional<Bike> getBikeByLicenceNumber(String licenceNumber) {
        return bikeRepository.getBikeByLicenceNumber(licenceNumber);
    }

    public List<Bike> getBikesByColor(String color) {
        return bikeRepository.getBikesByColor(color);
    }

    public List<Bike> getBikesByStolenStatus(Boolean status) {
        return bikeRepository.getBikeByStolenStatus(status);
    }

    public List<Bike> getBikesByType(String type) {
        return bikeRepository.getBikesByType(type);
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public void updateStatusBike(Bike bike) {
        bikeRepository.save(bike);
    }

    @Override
    public void deleteBikeById(Long id) {
        bikeRepository.delete(getBikeById(id)
                .orElseThrow(() -> new EntityNotFoundException("ERROR!: Can't delete Bike with ID: "
                        + id + " because can't find on BD."))
        );
    }
}
