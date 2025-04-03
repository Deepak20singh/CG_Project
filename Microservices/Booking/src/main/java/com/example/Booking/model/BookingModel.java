package com.example.Booking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BookingTable")
public class BookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int seatNumber;

    private String flightNumber;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    public BookingModel() {
    }

    public BookingModel(Long id, int seatNumber, String flightNumber, String firstName, String lastName, int age, String gender) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
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
}
