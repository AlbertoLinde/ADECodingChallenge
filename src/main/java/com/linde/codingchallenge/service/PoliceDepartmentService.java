package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.PoliceDepartment;

import java.util.List;

public interface PoliceDepartmentService {

    PoliceDepartment createDepartment(PoliceDepartment policeDepartment);

    List<PoliceDepartment> getAllDepartments();
}
