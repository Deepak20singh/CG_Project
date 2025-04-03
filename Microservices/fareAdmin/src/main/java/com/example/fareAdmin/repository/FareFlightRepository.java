package com.example.fareAdmin.repository;

import com.example.fareAdmin.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FareFlightRepository extends JpaRepository<DataModel, Long> {
    List<DataModel> findByDepartureAndArrival(String departure, String arrival);
    Optional<DataModel> findByFlightNumber(String flightNumber); // Ye method automatically SQL query banayega

}

