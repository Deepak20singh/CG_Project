package com.example.Booking.repository;

import com.example.Booking.model.BookingModel;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingModel,Long> {
    int countByFlightNumber(String flightNumber);
    List<BookingModel> findByFirstNameAndLastName(String firstName, String lastName);
    List<BookingModel> findByFirstName(String firstName);
}
