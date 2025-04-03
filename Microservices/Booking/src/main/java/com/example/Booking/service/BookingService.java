package com.example.Booking.service;

import com.example.Booking.dto.BookingDTO;
import com.example.Booking.model.BookingModel;
import com.example.Booking.repository.BookingRepository;
import com.example.Booking.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> add(BookingDTO dto) {
        DTO flight = getFlightDetails(dto.getFlightNumber());

        if (flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Flight " + dto.getFlightNumber() + " does not exist!");
        }

        // Check if available seats are greater than requested seat number
        if (dto.getSeatNumber() > flight.getSeats()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Only " + flight.getSeats() + " seats are available!");
        }

        // Create Booking Entry
        BookingModel booking = new BookingModel();
        booking.setSeatNumber(dto.getSeatNumber());
        booking.setFlightNumber(dto.getFlightNumber());
        booking.setFirstName(dto.getFirstName());
        booking.setLastName(dto.getLastName());
        booking.setAge(dto.getAge());
        booking.setGender(dto.getGender());

        bookingRepository.save(booking);

        return ResponseEntity.ok("Booking confirmed for Flight: " + dto.getFlightNumber());
    }

    private DTO getFlightDetails(String flightNumber) {
        String url = "http://localhost:8081/admin/flightnumber?number=" + flightNumber;
        try {
            return restTemplate.getForObject(url, DTO.class);
        } catch (Exception e) {
            return null;
        }
    }
    public ResponseEntity<String> deleting(Long id){
        if(bookingRepository.existsById(id)){
            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Booking Cancelled!!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Not Found!!!");
        }
    }
    public ResponseEntity<List<BookingDTO>> checkIn(String first, String second) {
            List<BookingModel> customers;

            if (second != null) {
                customers = bookingRepository.findByFirstNameAndLastName(first, second);
            } else {
                customers = bookingRepository.findByFirstName(first);
            }

            if (customers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }

            List<BookingDTO> ans = customers.stream()
                    .map(BookingDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ans);
        }
    }

