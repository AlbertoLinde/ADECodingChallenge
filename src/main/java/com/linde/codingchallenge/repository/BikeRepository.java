package com.linde.codingchallenge.repository;

import com.linde.codingchallenge.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    Optional<Bike> getBikeByLicenceNumber(String licenceNumber);

    List<Bike> getBikesByColor(String color);

    List<Bike> getBikesByType(String type);

    List<Bike> getBikeByStolenStatus(Boolean status);

}
