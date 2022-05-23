package com.linde.codingchallenge.service;

import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.repository.BikeRepository;
import com.linde.codingchallenge.util.ListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {


    private final BikeRepository bikeRepository;
    private final PoliceServiceImpl policeService;

    @Override
    public Bike createBike(Bike bike) {
        return bikeRepository.save(assignPoliceToNewBike(bike));
    }

    public Optional<Bike> findBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    public Optional<Bike> findBikeByLicenceNumber(String licenceNumber) {
        return bikeRepository.findBikeByLicenceNumber(licenceNumber);
    }

    public List<Bike> findBikesByColor(String color) {
        return bikeRepository.findBikesByColor(color);
    }

    public List<Bike> findBikesByCurrentStatus(Boolean status) {
        return bikeRepository.findBikesByStolenStatus(status);
    }

    public List<Bike> findBikeByType(String type) {
        return bikeRepository.findBikesByType(type);
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public List<Bike> findBikeByColorOrTypeOrStolenStatus(String color, String type, Boolean status) {
        return bikeRepository.findBikeByColorOrTypeOrStolenStatus(color, type, status);
    }

    @Override
    public void deleteAllBike() {
        bikeRepository.deleteAll();
    }

    @Override
    public void updateBike(Bike bike) {
        bikeRepository.save(bike);
    }

    @Override
    public Bike bikeFound(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBikeById(Long id) {
        bikeRepository.delete(findBikeById(id)
                .orElseThrow(() -> new EntityNotFoundException("ERROR!: Can't delete Bike with ID: "
                        + id + " because can't find on BD."))
        );
    }

    private Bike assignPoliceToNewBike(Bike bike) {
        List<Police> freePolices = policeService.findAllPoliceNotInvestigating();
        if (!freePolices.isEmpty()) {
            Police policeAssigned = freePolices.stream()
                    .findAny()
                    .get();

            policeAssigned.setInvestigating(true);
            bike.setPolice(policeAssigned);
            policeService.updatePolice(policeAssigned);
        }
        return bike;
    }

    public Bike updateStatusBike(Long bikeId) {
        Bike bike = findBikeById(bikeId)
                .orElse(null);
        if (bike != null) {
            bike.setPolice(null);
            bike.setStolenStatus(false);
            bikeRepository.save(bike);
        }
        return bike;
    }

    // TODO: Maybe move to Police Service
    public List<Bike> assignPoliceOfficerToStolenBikes() {
        List<Bike> stolenBikes = findBikesByCurrentStatus(true);
        List<Police> freePoliceOfficers = policeService.findAllPoliceNotInvestigating();
        return ListUtil.zipList(freePoliceOfficers, stolenBikes, (policeOfficer, bike) -> {
            policeOfficer.setInvestigating(true);
            bike.setStolenStatus(true);
            bike.setPolice(policeOfficer);
            updateBike(bike);
            return bike;
        });
    }
}
