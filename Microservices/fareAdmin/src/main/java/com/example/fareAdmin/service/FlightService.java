package com.example.fareAdmin.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.fareAdmin.dto.DTO;
import com.example.fareAdmin.model.DataModel;
import com.example.fareAdmin.repository.FareFlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    private final FareFlightRepository fareFlightRepository;

    public FlightService(FareFlightRepository fareFlightRepository) {
        this.fareFlightRepository = fareFlightRepository;
    }

    public ResponseEntity<DTO> add(DTO dto) {
        try {
            DataModel dataModel = new DataModel(
                    dto.getId(),
                    dto.getFlightNumber(),
                    dto.getAirline(),
                    dto.getDeparture(),
                    dto.getArrival(),
                    dto.getFare(),
                    dto.getDepartureTime(),
                    dto.getArrivalTime(),
                    dto.getSeats()
            );

            DataModel saved = fareFlightRepository.save(dataModel);
            return ResponseEntity.ok(new DTO(saved));

        } catch (Exception e) {
            logger.error("Error while adding flight: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Ya custom error DTO return kar sakta hai
        }
    }
    public ResponseEntity<List<DataModel>> all() {
        return ResponseEntity.ok(fareFlightRepository.findAll());
    }
  public ResponseEntity<String> delete(Long id) {
        if (fareFlightRepository.existsById(id)) {
            fareFlightRepository.deleteById(id);
            return ResponseEntity.ok("Flight Cancelled!!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight Not Exist");
    }

     public ResponseEntity<DTO> update(Long id, DTO dto) {
        Optional<DataModel> existingModelOptional = fareFlightRepository.findById(id);

        if (existingModelOptional.isPresent()) {
            DataModel existingModel = existingModelOptional.get();

            existingModel.setFlightNumber(dto.getFlightNumber());
            existingModel.setAirline(dto.getAirline());
            existingModel.setDeparture(dto.getDeparture());
            existingModel.setArrival(dto.getArrival());
            existingModel.setFare(dto.getFare());
            existingModel.setDepartureTime(dto.getDepartureTime());
            existingModel.setArrivalTime(dto.getArrivalTime());
            existingModel.setSeats(dto.getSeats());

            DataModel updatedModel = fareFlightRepository.save(existingModel);
            return ResponseEntity.ok(new DTO(updatedModel));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    public ResponseEntity<List<DTO>> getFlightLocation(String departure,String arrival){
        List<DataModel> flights = fareFlightRepository.findByDepartureAndArrival(departure, arrival);

        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        List<DTO> flightDto=flights.stream().map(x->new DTO(x)).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(flightDto);
    }

    public ResponseEntity<?> getInfo(String number){
        Optional<DataModel> flight = fareFlightRepository.findByFlightNumber(number);

        if (flight.isPresent()) {
            DTO flightDTO = new DTO(flight.get()); // Entity ko DTO me convert karna
            return ResponseEntity.ok(flightDTO);
        } else {
            return ResponseEntity.status(404).body("Flight Not Found!");
        }
    }

}
