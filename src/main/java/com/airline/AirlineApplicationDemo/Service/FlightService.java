package com.airline.AirlineApplicationDemo.Service;

import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository repository;

    public Flight addFlight(Flight flight){
        return repository.save(flight);
    }

    public List<Flight> fetchFlightWithSorted(){
        return repository.findAll();
    }

    public Flight fetchFlightById(Integer flightNumber) {
        return (repository.findById(flightNumber))
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id " + flightNumber));
    }
}
