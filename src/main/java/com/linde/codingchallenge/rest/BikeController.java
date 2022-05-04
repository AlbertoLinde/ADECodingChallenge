package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.service.BikeServiceImpl;
import com.linde.codingchallenge.service.PoliceServiceImpl;
import com.linde.codingchallenge.util.ListUtil;
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
    public ResponseEntity<Bike> newBike(@RequestBody Bike bike) {
        List<Police> freePolices = policeService.getAllPolicesNotInvestigating();
        if (!freePolices.isEmpty()) {
            Police policeAssigned = freePolices.stream().findAny().get();
            policeAssigned.setInvestigating(true);
            bike.setPolice(policeAssigned);
            policeService.updatePolice(policeAssigned);
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

    @PutMapping("/bikes/assign-polices")
    public ResponseEntity<List<Bike>> assignFreePoliceToStolenBikes() {
        List<Police> freePolices = policeService.getAllPolicesNotInvestigating();
        List<Bike> stolenBikes = bikeService.getBikesByStolenStatus(true);

        if (!freePolices.isEmpty() && !stolenBikes.isEmpty()) {
            List<Bike> bikesAssigned = ListUtil.zipList(freePolices, stolenBikes, (police, bike) -> {
                police.setInvestigating(true);
                bike.setStolenStatus(true);
                bike.setPolice(police);
                return bike;
            });
            return ResponseEntity.ok().body(bikesAssigned);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/bike/found-bike/{id}")
    public ResponseEntity<?> bikeHasBeenFound(@PathVariable("id") Long id) {
        Optional<Bike> bike = bikeService.getBikeById(id);
        if (bike.isPresent()) {
            Bike foundBike = bike.get();
            Police policeInvestigating = bike.get().getPolice();
            policeInvestigating.setInvestigating(false);
            foundBike.setPolice(null);
            foundBike.setStolenStatus(false);

            policeService.updatePolice(policeInvestigating);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(bikeService.bikeFound(foundBike));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("ERROR: Can't find the Bike with ID: " + id + " in the DB.");
    }

}
