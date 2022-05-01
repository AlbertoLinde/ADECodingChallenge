package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeService {

    Bike createBike(Bike bike);

    void deleteBikeById(Long id);

    //Optional<Bike> getBikeById(Long id);

    List<Bike> getAllBikes();

}
