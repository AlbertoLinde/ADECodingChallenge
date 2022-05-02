package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.entity.PoliceDepartment;
import com.linde.codingchallenge.repository.PoliceRepository;
import com.linde.codingchallenge.service.PoliceDepartmentServiceImpl;
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
    private final PoliceDepartmentServiceImpl policeDepartmentService;

    private final PoliceRepository policeRepository;

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

    @GetMapping(value = "/polices/investigating")
    public List<Police> getAllPolicesInvestigating() {
        return policeService.getAllPolicesInvestigating();
    }

    @GetMapping(value = "/polices/not_investigating")
    public List<Police> getAllPolicesNotInvestigating() {
        return policeService.getAllPolicesNotInvestigating();
    }

    @PutMapping("/polices/{policeId}/{departmentId}")
    public ResponseEntity<?> policeDepartment(@PathVariable("policeId") Long policeId, @PathVariable("departmentId") Long departmentId) {

        Optional<PoliceDepartment> policeDepartment = policeDepartmentService.getPoliceDepartmentById(departmentId);
        Optional<Police> police = policeService.getPoliceById(policeId);

        if (policeDepartment.isPresent() && police.isPresent()) {
            Police pol = police.get();
            pol.setPoliceDepartment(policeDepartment.get());
            return new ResponseEntity<>(policeRepository.save(pol), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
