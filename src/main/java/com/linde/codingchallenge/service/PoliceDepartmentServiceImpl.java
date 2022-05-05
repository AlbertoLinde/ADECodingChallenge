package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.PoliceDepartment;
import com.linde.codingchallenge.repository.PoliceDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceDepartmentServiceImpl implements PoliceDepartmentService {

    @Autowired
    private PoliceDepartmentRepository policeDepartmentRepository;


    @Override
    public PoliceDepartment createDepartment(PoliceDepartment policeDepartment) {
        return policeDepartmentRepository.save(policeDepartment);
    }

    @Override
    public List<PoliceDepartment> findAllDepartments() {
        return policeDepartmentRepository.findAll();
    }

    @Override
    public Optional<PoliceDepartment> findPoliceDepartmentById(Long id) {
        return policeDepartmentRepository.findById(id);
    }


}
