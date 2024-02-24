package com.airline.AirlineApplicationDemo.Repository;

import com.airline.AirlineApplicationDemo.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
