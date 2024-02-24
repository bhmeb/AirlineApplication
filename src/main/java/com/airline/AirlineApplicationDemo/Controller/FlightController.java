package com.airline.AirlineApplicationDemo.Controller;

import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/airline"})
public class FlightController {

    @Autowired
    private FlightService service;

    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
      Flight response = service.addFlight(flight);
      if(response!=null){
          LOG.info("****New flight successfully added****");
          return new ResponseEntity<>(response, HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping(value = "/retrieve")
    public ResponseEntity<List<Flight>> fetchFlight(){
        List<Flight> response = service.fetchFlight();
        List<Flight> res = response.stream()
                .sorted(Comparator.comparing(Flight::getDuration))
                .collect(Collectors.toList());

        if(res!=null){
            LOG.info("*****List of Flights***");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/retrieveById/{flightNumber}")
    public ResponseEntity<List<Flight>> fetchFlightById(@PathVariable Integer flightNumber) {

        Flight response = service.fetchFlightById(flightNumber);

        List<Flight> res = Arrays.asList(response)
                .stream()
                .collect(Collectors.toList());

        if (res != null) {
            LOG.info("*****List of Flights***" + res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            LOG.info("*****List of Flights***" + flightNumber);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
