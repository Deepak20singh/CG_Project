package com.example.Booking.dto;

import com.example.Booking.model.BookingModel;
import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    private int seatNumber;
    private String flightNumber;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    public BookingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Constructor for Converting Entity → DTO
    public BookingDTO(BookingModel booking) {
        this.id = booking.getId();
        this.seatNumber = booking.getSeatNumber();
        this.flightNumber = booking.getFlightNumber();
        this.firstName = booking.getFirstName();
        this.lastName = booking.getLastName();
        this.age = booking.getAge();
        this.gender = booking.getGender();
    }
}
