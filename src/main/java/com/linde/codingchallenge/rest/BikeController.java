package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.service.BikeServiceImpl;
import com.linde.codingchallenge.service.EmailSenderServiceImpl;
import com.linde.codingchallenge.service.PoliceServiceImpl;
import com.linde.codingchallenge.util.ListUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BikeController {

    private final BikeServiceImpl bikeService;
    private final PoliceServiceImpl policeService;
    private final EmailSenderServiceImpl emailSenderService;

    /**
     * Create new Bike in the Database (If the bike are created and one police are available is assigned automatic)
     *
     * @param bike @RequestBody Bike
     * @return ResponseEntity
     */
    @Operation(summary = "Create new Stolen Bike")
    @PostMapping(value = "/add-bike")
    public ResponseEntity<Bike> newBike(@RequestBody Bike bike) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bikeService.createBike(bike));
    }

    @Operation(summary = "Find Bike by ID")
    @GetMapping(value = "/bike/{id}")
    public ResponseEntity<?> findBikeById(@PathVariable("id") Long id) {
        Optional<Bike> bike = bikeService.findBikeById(id);
        if (bike.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: The bike with the ID: " + id + " no exist.");
        }
        return ResponseEntity.ok(bike);
    }

    /**
     * Get bike using the Licence Number
     *
     * @param licenceNumber String licence number
     * @return Bike if the wanted bike exist in the DB or HTTP_NOT_FOUND if not exist.
     */
    @Operation(summary = "Find Bike by the Licence Number")
    @GetMapping(value = "/bike/licence-number/{licenceNumber}")
    public ResponseEntity<?> findBikeByLicenceNumber(@PathVariable("licenceNumber") String licenceNumber) {
        Optional<Bike> bike = bikeService.findBikeByLicenceNumber(licenceNumber);
        if (bike.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: The bike with the Licence Number: " + licenceNumber + " no exist.");
        }
        return ResponseEntity.ok(bike);
    }

    @Operation(summary = "Find Bike by Color")
    @GetMapping(value = "/bike/color/{color}")
    public List<Bike> getBikeByColor(@PathVariable("color") String color) {
        return bikeService.findBikesByColor(color);
    }

    @Operation(summary = "Find Bike by type")
    @GetMapping(value = "/bike/type/{type}")
    public List<Bike> getBikeByType(@PathVariable("type") String type) {
        return bikeService.findBikeByType(type);
    }

    @Operation(summary = "Find all Bikes")
    @GetMapping(value = "/bikes")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @Operation(summary = "Find bikes with Stolen Status true")
    @GetMapping(value = "/bikes/stolen-status/{stolen-status}")
    public List<Bike> getBikesByStolenStatus(@PathVariable("stolen-status") Boolean status) {
        return bikeService.findBikesByCurrentStatus(status);
    }

    @Operation(summary = "Find Bikes by Color, Type OR Status")
    @GetMapping(value = "/bikes/findByColorOrTypeOrStatus")
    public ResponseEntity<List<Bike>> findBikesByColorOrTypeOrStolenStatus(@RequestParam String color,
                                                                           @RequestParam String type,
                                                                           @RequestParam Boolean status) {
        return ResponseEntity.ok(bikeService.findBikeByColorOrTypeOrStolenStatus(color, type, status));
    }

    @Operation(summary = "Update Stolen status")
    @PutMapping("/bike/{id}")
    public ResponseEntity<?> updateStatusBike(@PathVariable("id") Long id) {
        Bike bike = bikeService.updateStatusBike(id);
        if (bike != null) {
            return ResponseEntity.ok(bike);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Check if there are free police and stolen bikes and if its correct then assign polices to bikes directly.
     *
     * @return List of bikes
     */
    @Operation(summary = "Assign free police officers to stolen bikes")
    @PutMapping("/bikes/assign-polices")
    public ResponseEntity<List<Bike>> assignFreePoliceToStolenBikes() {
        List<Bike> assignedBikes = bikeService.assignPoliceOfficerToStolenBikes();
        if (!assignedBikes.isEmpty()) {
            return ResponseEntity.ok().body(bikeService.assignPoliceOfficerToStolenBikes());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * The status of the bicycle is changed to found and the police officer is free
     * Optional I can just change the status of the police officer and delete from the table the bike.
     *
     * @param id ID of the bike
     * @return ResponseEntity with the status
     */
    @Operation(summary = "Set bike has been found by officer")
    @PutMapping("/bike/found-bike/{id}")
    public ResponseEntity<?> bikeHasBeenFound(@PathVariable("id") Long id) {
        Bike bike = bikeService.bikeHasBeenFound(id);
        if (bike != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bike);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("ERROR: Can't find the Bike with ID: " + id + " in the DB.");
    }

    @Operation(summary = "Delete bike from the database.")
    // The challenge no ask to add Delete method, but I do.
    @DeleteMapping("/bike/{id}")
    public ResponseEntity<?> deleteBike(@PathVariable(value = "id") Long id) {
        if (bikeService.findBikeById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: Can't delete the bike with ID: " + id);
        }
        bikeService.deleteBikeById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete all Bikes, just for testing porpouse.")
    @DeleteMapping("/delete-bikes")
    public void deleteBikes() {
        bikeService.deleteAllBike();
    }

}

