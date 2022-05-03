package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.entity.PoliceDepartment;
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

    @PostMapping(value = "/add-police")
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

    @GetMapping(value = "/polices")
    public List<Police> getAllPolices() {
        return policeService.getAllPolices();
    }

    @GetMapping(value = "/polices/investigating")
    public List<Police> getAllPolicesInvestigating() {
        return policeService.getAllPolicesInvestigating();
    }

    @GetMapping(value = "/polices/not-investigating")
    public List<Police> getAllPolicesNotInvestigating() {
        return policeService.getAllPolicesNotInvestigating();
    }

    @PutMapping("/police/{policeId}/department/{departmentId}")
    public ResponseEntity<?> assignPoliceDepartmentToPolice(@PathVariable("policeId") Long policeId, @PathVariable("departmentId") Long departmentId) {

        Optional<PoliceDepartment> optPoliceDepartment = policeDepartmentService.getPoliceDepartmentById(departmentId);
        Optional<Police> optPolice = policeService.getPoliceById(policeId);

        if (optPoliceDepartment.isPresent() && optPolice.isPresent()) {
            Police police = optPolice.get();
            police.setPoliceDepartment(optPoliceDepartment.get());
            return new ResponseEntity<>(policeService.updatePolice(police), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
