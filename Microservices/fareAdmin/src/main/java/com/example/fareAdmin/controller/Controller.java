package com.example.fareAdmin.controller;

import com.example.fareAdmin.dto.DTO;
import com.example.fareAdmin.model.DataModel;
import com.example.fareAdmin.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/admin")
@RestController
public class Controller {
    @GetMapping("/")
    public String hello(){
        return "Admin";
    }
    @Autowired
    private FlightService flightService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DataModel>> all(){
        return flightService.all();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return flightService.delete(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<DTO> add(@RequestBody DTO dto){
        return flightService.add(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<DTO> update(@PathVariable Long id,@RequestBody DTO dto){
        return flightService.update(id,dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/flights")
    public ResponseEntity<List<DTO>> getFlights(@RequestParam(required = false) String departure,@RequestParam(required = false) String arrival){
        return flightService.getFlightLocation(departure,arrival);
    }
    @GetMapping("/flightnumber")
    public ResponseEntity<?> getFlights(@RequestParam(required = false) String number){
        return flightService.getInfo(number);
    }
}
