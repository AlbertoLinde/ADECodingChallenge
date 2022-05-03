package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.PoliceDepartment;

import java.util.List;
import java.util.Optional;

public interface PoliceDepartmentService {

    PoliceDepartment createDepartment(PoliceDepartment policeDepartment);

    List<PoliceDepartment> getAllDepartments();

    Optional<PoliceDepartment> getPoliceDepartmentById(Long id);

}
