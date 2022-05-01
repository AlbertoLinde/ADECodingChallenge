package com.linde.codingchallenge.repository;

import com.linde.codingchallenge.entity.PoliceDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliceDepartmentRepository extends JpaRepository<PoliceDepartment, Long> {
}
