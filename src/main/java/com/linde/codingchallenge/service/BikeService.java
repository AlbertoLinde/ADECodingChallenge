package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;

import java.util.List;

public interface BikeService {

    Bike createBike(Bike bike);

    void deleteBikeById(Long id);

    List<Bike> getAllBikes();

    Bike bikeFound(Bike bike);

    List<Bike> findBikeByColorOrTypeOrStolenStatus(String color, String type, Boolean stolenStatus);

}
