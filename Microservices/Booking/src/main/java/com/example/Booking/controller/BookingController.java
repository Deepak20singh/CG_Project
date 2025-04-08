package com.example.Booking.controller;

import com.example.Booking.dto.BookingDTO;
import com.example.Booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @PutMapping("/add")
    public ResponseEntity<String> add(@RequestBody BookingDTO bookingDTO){
        return bookingService.add(bookingDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return bookingService.deleting(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    @GetMapping("/checkin")
    public ResponseEntity<List<BookingDTO>> show(@RequestParam(required = true) String first,@RequestParam(required = false) String second){
        return bookingService.checkIn(first,second);
    }


}
