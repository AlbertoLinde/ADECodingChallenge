package com.linde.codingchallenge.repository;

import com.linde.codingchallenge.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliceRepository extends JpaRepository<Police, Long> {

    List<Police> findAllPoliceByInvestigatingFalse();

    List<Police> findAllPoliceByInvestigatingTrue();

}
