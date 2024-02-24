package com.airline.AirlineApplicationDemo.Service;

import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository repository;

    public Flight addFlight(Flight flight){
        return repository.save(flight);
    }

    public List<Flight> fetchFlight(){
        return repository.findAll();
    }

    public Flight fetchFlightById(Integer flightNumber) {
        return repository.findById(flightNumber)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id " + flightNumber));
    }
}
