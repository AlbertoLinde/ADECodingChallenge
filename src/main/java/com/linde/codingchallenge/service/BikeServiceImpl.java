package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {


    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Optional<Bike> findBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    public Optional<Bike> findBikeByLicenceNumber(String licenceNumber) {
        return bikeRepository.findBikeByLicenceNumber(licenceNumber);
    }

    public List<Bike> findBikesByColor(String color) {
        return bikeRepository.findBikesByColor(color);
    }

    public List<Bike> findBikesByCurrentStatus(Boolean status) {
        return bikeRepository.findBikesByStolenStatus(status);
    }

    public List<Bike> findBikeByType(String type) {
        return bikeRepository.findBikesByType(type);
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public List<Bike> findBikeByColorOrTypeOrStolenStatus(String color, String type, Boolean status) {
        return bikeRepository.findBikeByColorOrTypeOrStolenStatus(color, type, status);
    }

    @Override
    public void deleteAllBike() {
        bikeRepository.deleteAll();
    }

    @Override
    public void updateBike(Bike bike) {
        bikeRepository.save(bike);
    }

    @Override
    public Bike bikeFound(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBikeById(Long id) {
        bikeRepository.delete(findBikeById(id)
                .orElseThrow(() -> new EntityNotFoundException("ERROR!: Can't delete Bike with ID: "
                        + id + " because can't find on BD."))
        );
    }
}
