package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.service.PoliceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PoliceController {

    private final PoliceServiceImpl policeService;

    @PostMapping(value = "/addPolice")
    public ResponseEntity<?> newPolice(@RequestBody Police police) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(policeService.createPolice(police));
    }

    @GetMapping(value = "/police/{id}")
    public ResponseEntity<?> getPoliceById(@PathVariable("id") Long id) {
        Optional<Police> police = policeService.getPoliceById(id);
        if (police.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(police);
    }

    @GetMapping(value = "/police/stolen_bike/{id}")
    public ResponseEntity<?> getPoliceByStolenBike(@PathVariable("id") Long id) {
        Optional<Police> police = policeService.getPoliceByStolenBike(id);
        if (police.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(police);
    }

    @GetMapping(value = "/polices")
    public List<Police> getAllPolices() {
        return policeService.getAllPolices();
    }

    @GetMapping(value = "/bikes/investigating")
    public List<Police> getAllPolicesInvestigating() {
        return policeService.getAllPolicesInvestigating();
    }

    @GetMapping(value = "/bikes/not_investigating")
    public List<Police> getAllPolicesNotInvestigating() {
        return policeService.getAllPolicesNotInvestigating();
    }

}
