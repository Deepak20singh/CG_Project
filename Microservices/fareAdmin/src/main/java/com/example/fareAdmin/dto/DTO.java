package com.example.fareAdmin.dto;

import com.example.fareAdmin.model.DataModel;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@AllArgsConstructor
public class DTO {
    //class for flight

    private Long id;

    private String flightNumber;
    private String airline;
    private String departure;
    private String arrival;
    private Double fare;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalTime;

    private Integer seats;

    public DTO(DataModel dto){

        this.id = dto.getId();
        this.flightNumber =  dto.getFlightNumber();
        this.airline = dto.getAirline();
        this.departure = dto.getDeparture();
        this.arrival = dto.getArrival();
        this.fare = dto.getFare();
        this.departureTime = dto.getDepartureTime();
        this.arrivalTime = dto.getArrivalTime();
        this.seats = dto.getSeats();

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
public DTO(){}
}
