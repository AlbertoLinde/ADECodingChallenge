package com.linde.codingchallenge.rest;

import com.linde.codingchallenge.entity.PoliceDepartment;
import com.linde.codingchallenge.service.PoliceDepartmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PoliceDepartmentController {

    private final PoliceDepartmentServiceImpl policeDepartmentService;

    @PostMapping(value = "/add-department")
    public ResponseEntity<?> newDepartment(@RequestBody PoliceDepartment policeDepartment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(policeDepartmentService.createDepartment(policeDepartment));
    }

    @GetMapping(value = "/department/{id}")
    public ResponseEntity<?> getPoliceDepartmentById(@PathVariable("id") Long id) {
        Optional<PoliceDepartment> police = policeDepartmentService.getPoliceDepartmentById(id);
        if (police.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(police);
    }

    @GetMapping(value = "/departments")
    public List<PoliceDepartment> getAllDepartments() {
        return policeDepartmentService.getAllDepartments();
    }

}
