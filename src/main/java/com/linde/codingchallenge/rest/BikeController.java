package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.service.BikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BikeController {

    private final BikeServiceImpl bikeService;

    @PostMapping(value = "/addBike")
    public ResponseEntity<?> newBike(@RequestBody Bike bike) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bikeService.createBike(bike));
    }

    // The challenge no ask to add Delete method, but I do.
    @DeleteMapping("/bike/{id}")
    public ResponseEntity<?> deleteBike(@PathVariable(value = "id") Long id) {
        if (bikeService.getBikeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        bikeService.deleteBikeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/bikes/{id}")
    public ResponseEntity<?> getBikeById(@PathVariable("id") Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        if (bike.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @GetMapping(value = "/bikes/licence_number/{licenceNumber}")
    public ResponseEntity<?> getBikeByLicenceNumber(@PathVariable("licenceNumber") String licenceNumber) {
        Optional<Bike> bike = bikeService.getBikeByLicenceNumber(licenceNumber);
        if (bike.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @GetMapping(value = "/bikes")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping(value = "/bikes/color/{color}")
    public List<Bike> getBikeByColor(@PathVariable("color") String color) {
        return bikeService.getBikesByColor(color);
    }

    @GetMapping(value = "/bikes/type/{type}")
    public List<Bike> getBikeByType(@PathVariable("type") String type) {
        return bikeService.getBikesByType(type);
    }

    @GetMapping(value = "/bikes/stolen_status/{stolenStatus}")
    public List<Bike> getBikesByStolenStatus(@PathVariable("stolenStatus") Boolean stolenStatus) {
        return bikeService.getBikesByStolenStatus(stolenStatus);
    }

}
