package com.airline.AirlineApplicationDemo.Controller;

import com.airline.AirlineApplicationDemo.Exception.ApplicationException;
import com.airline.AirlineApplicationDemo.Exception.RecordAlreadyExistException;
import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Repository.FlightRepository;
import com.airline.AirlineApplicationDemo.Service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/airline"})
public class FlightController {

    @Autowired
    private FlightService service;
    @Autowired
    FlightRepository repository;

    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {

        List<Flight> response = Arrays.asList(flight).stream()
                .collect(Collectors.toList());

        Flight resFromDB = service.fetchFlightById(flight.getFlightNumber());

        try {
            if (response!=null) {
                flight.setDuration(flight.getDuration()+" Minutes");
                if(resFromDB.getFlightNumber()!=flight.getFlightNumber()){
                    service.addFlight(flight);
                    return new ResponseEntity<>(flight, HttpStatus.CREATED);
                }else{
                    LOG.error("Record already exists!!!"+ flight.getFlightNumber());
                    return new ResponseEntity<>(HttpStatus.valueOf("Record already exists!!!"));
                }
            } else {
                throw new ApplicationException("Response empty {} ");
            }
        } catch (RecordAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/retrieve")
    public ResponseEntity<List<Flight>> fetchFlightWithSorted() {
        List<Flight> response = service.fetchFlightWithSorted();
        List<Flight> res = response.stream()
                .sorted(Comparator.comparing(Flight::getDuration))
                .collect(Collectors.toList());

        if (res != null) {
            LOG.info("Available Flights with sort duration order first");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/retrieveById/{flightNumber}")
    public ResponseEntity<Flight> fetchFlightById(@PathVariable Integer flightNumber) {
        try {
            Flight response = service.fetchFlightById(flightNumber);
            Optional<Integer> flightByNumber = Arrays.asList(response).stream()
                    .map(Flight::getFlightNumber)
                    .min(Comparator.comparing(Function.identity()));
            if (flightByNumber.isPresent()) {
                LOG.info("List of Flights {}");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                LOG.error("No flight found with this ID {}" + flightByNumber);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
