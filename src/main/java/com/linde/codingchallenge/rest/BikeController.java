package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.service.BikeServiceImpl;
import com.linde.codingchallenge.service.PoliceServiceImpl;
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

    private final PoliceServiceImpl policeService;

    @PostMapping(value = "/addBike")
    public ResponseEntity<?> newBike(@RequestBody Bike bike) {
        List<Police> freePolices = policeService.getAllPolicesNotInvestigating();
        if (!freePolices.isEmpty()) {
            Police police = freePolices.stream().findAny().get();
            police.setInvestigating(true);
            bike.setPolice(police);
        }
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

    @GetMapping(value = "/bike/{id}")
    public ResponseEntity<?> getBikeById(@PathVariable("id") Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        if (bike.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @GetMapping(value = "/bike/licence-number/{licenceNumber}")
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

    @GetMapping(value = "/bike/color/{color}")
    public List<Bike> getBikeByColor(@PathVariable("color") String color) {
        return bikeService.getBikesByColor(color);
    }

    @GetMapping(value = "/bike/type/{type}")
    public List<Bike> getBikeByType(@PathVariable("type") String type) {
        return bikeService.getBikesByType(type);
    }

    @GetMapping(value = "/bikes/stolen-status/{stolen-status}")
    public List<Bike> getBikesByStolenStatus(@PathVariable("stolen-status") Boolean status) {
        return bikeService.getBikesByStolenStatus(status);
    }

    @PutMapping("/bike/{id}")
    public ResponseEntity<?> updateStatusBike(@PathVariable("id") Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        if (bike.isPresent()) {
            Bike stolenBike = bike.get();
            stolenBike.setPolice(null);
            stolenBike.setStolenStatus(false);
            return ResponseEntity.ok(stolenBike);
        }
        return ResponseEntity.notFound().build();
    }

}
