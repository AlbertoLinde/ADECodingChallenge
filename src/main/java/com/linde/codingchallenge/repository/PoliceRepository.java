package com.linde.codingchallenge.repository;

import com.linde.codingchallenge.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PoliceRepository extends JpaRepository<Police, Long> {

    Optional<Police> findPoliceByStolenBike(Long id);

    List<Police> findAllPoliceByInvestigatingFalse();

    List<Police> findAllPoliceByInvestigatingTrue();

}
