package com.linde.codingchallenge.repository;

import com.linde.codingchallenge.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    Optional<Bike> findBikeByLicenceNumber(String licenceNumber);

    List<Bike> findBikesByColor(String color);

    List<Bike> findBikesByType(String type);

    List<Bike> findBikesByStolenStatus(Boolean status);

    List<Bike> findBikeByColorOrTypeOrStolenStatus(String color, String type, Boolean status);

}
