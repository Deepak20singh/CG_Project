package com.example.fareAdmin.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity

@Table(name = "FlightInformation")
public class DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String airline;
    private String departure;
    private String arrival;
    private Double fare;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private Integer seats;

    public DataModel(Long id, String flightNumber, String airline, String departure, String arrival, Double fare, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer seats) {
        this.id=id;
       this.flightNumber = flightNumber;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.fare = fare;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public DataModel(){

    }
}
