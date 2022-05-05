package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.entity.PoliceDepartment;
import com.linde.codingchallenge.service.PoliceDepartmentServiceImpl;
import com.linde.codingchallenge.service.PoliceServiceImpl;
import lombok.RequiredArgsConstructor;
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

    /**
     * Create new Police Officer
     *
     * @param police Police
     * @return Response Entity with the status
     */
    @PostMapping(value = "/add-police")
    public ResponseEntity<?> newPolice(@RequestBody Police police) {
        return ResponseEntity.ok(policeService.createPolice(police));
    }

    @GetMapping(value = "/police/{id}")
    public ResponseEntity<?> findPoliceById(@PathVariable("id") Long id) {
        Optional<Police> police = policeService.findPoliceById(id);
        if (police.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(police);
    }

    @GetMapping(value = "/police-officers")
    public List<Police> findAllPoliceOfficers() {
        return policeService.findAllPolice();
    }

    @GetMapping(value = "/police/investigating")
    public List<Police> findPoliceOfficersInvestigating() {
        return policeService.findAllPoliceInvestigating();
    }

    @GetMapping(value = "/police/not-investigating")
    public List<Police> getAllPolicesNotInvestigating() {
        return policeService.findAllPoliceNotInvestigating();
    }

    /**
     * Assign one department to the selected police
     *
     * @param policeId     Police to assign the department
     * @param departmentId Department
     * @return Response Entity with the status
     */
    @PutMapping("/police/{policeId}/department/{departmentId}")
    public ResponseEntity<?> assignPoliceDepartmentToPolice(@PathVariable("policeId") Long policeId,
                                                            @PathVariable("departmentId") Long departmentId) {

        Optional<PoliceDepartment> optPoliceDepartment = policeDepartmentService.findPoliceDepartmentById(departmentId);
        Optional<Police> optPolice = policeService.findPoliceById(policeId);

        if (optPoliceDepartment.isPresent() && optPolice.isPresent()) {
            Police police = optPolice.get();
            police.setPoliceDepartment(optPoliceDepartment.get());
            return ResponseEntity.ok(policeService.updatePolice(police));
        }
        return ResponseEntity.notFound().build();
    }

}
