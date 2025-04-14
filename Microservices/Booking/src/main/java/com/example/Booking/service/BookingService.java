package com.example.Booking.service;

import com.example.Booking.configuration.RabbitMQConfig;
import com.example.Booking.connection.UserContext;
import com.example.Booking.dto.BookingDTO;
import com.example.Booking.dto.EmailDTO;
import com.example.Booking.model.BookingModel;
import com.example.Booking.repository.BookingRepository;
import com.example.Booking.dto.DTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final BookingRepository bookingRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public BookingService(BookingRepository bookingRepository, RabbitTemplate rabbitTemplate) {
        this.bookingRepository = bookingRepository;
        this.rabbitTemplate = rabbitTemplate;
    }
    public ResponseEntity<String> add(BookingDTO dto) {
        DTO flight = getFlightDetails(dto.getFlightNumber());

        if (flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Flight " + dto.getFlightNumber() + " does not exist!");
        }

        if (dto.getSeatNumber() > flight.getSeats()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Only " + flight.getSeats() + " seats are available!");
        }

        BookingModel booking = new BookingModel();
        booking.setSeatNumber(dto.getSeatNumber());
        booking.setFlightNumber(dto.getFlightNumber());
        booking.setFirstName(dto.getFirstName());
        booking.setLastName(dto.getLastName());
        booking.setAge(dto.getAge());
        booking.setGender(dto.getGender());

        bookingRepository.save(booking);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(UserContext.getEmail());  
        emailDTO.setSubject("Booking Confirmation - " + dto.getFlightNumber());
        emailDTO.setBody("Hi " + dto.getFirstName() + ", your booking for flight " + dto.getFlightNumber() + " is confirmed."+"AT seat:-"+dto.getSeatNumber());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailDTO);


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

            EmailDTO emailDTO=new EmailDTO();
            emailDTO.setTo(UserContext.getEmail());
            emailDTO.setSubject("Booking Cancel ");
            emailDTO.setBody("Hii, your flight booking has been successfully cancelled!!" +
                    "Thank you for choosing us...");

            rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE,emailDTO);


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

