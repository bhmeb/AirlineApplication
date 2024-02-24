package com.airline.AirlineApplicationDemo.Service;

import com.airline.AirlineApplicationDemo.Controller.FlightController;
import com.airline.AirlineApplicationDemo.Exception.ApplicationException;
import com.airline.AirlineApplicationDemo.Model.Destination;
import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Model.Origin;
import com.airline.AirlineApplicationDemo.Repository.FlightRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTest {

    @InjectMocks
    FlightService service;

    @Mock
    FlightRepository repository;
    @Mock
    FlightController controller;

    @Before
    public void init() {
    }


    @Test
    public void addFlightServiceTest() {

        Flight flight = new Flight();
        assertNotNull(flight);
        flight.setFlightNumber(1001);
        flight.setOrigin(new Origin("Kolkata", "CCU"));
        flight.setDestination(new Destination("Mumbai", "BOM"));
        flight.setDuration("30");

        List<Flight> flightList = List.of(flight);

        try {
            List<Flight> res = flightList.stream().collect(Collectors.toList());
            if (res != null) {
                System.out.println("Data not empty: " + res);
                Mockito.when(repository.save(flight)).thenReturn(flight);
                controller.addFlight(flight);
            }
        } catch (Exception e) {
            assertTrue(e instanceof ApplicationException);
        }
    }
    @Test
    public void fetchFlightServiceTest() {
        Flight flight1 = new Flight();
        flight1.setFlightNumber(1001);
        flight1.setOrigin(new Origin("Kolkata", "CCU"));
        flight1.setDestination(new Destination("Mumbai", "BOM"));
        flight1.setDuration("30");

        Flight flight2 = new Flight();
        flight2.setFlightNumber(1001);
        flight2.setOrigin(new Origin("Mumbai", "BOM"));
        flight2.setDestination(new Destination("Bhubaneswar", "BBI"));
        flight2.setDuration("30");

        assertNotNull(flight1);
        assertNotNull(flight2);

        List<Flight> flightList = Arrays.asList(flight1, flight2);

        try {
            Optional<Flight> mockData = flightList.stream().min(Comparator.comparing(Flight::getDuration));
            if (mockData.isPresent()) {
                System.out.println("Data not empty: " + mockData.get());
                Mockito.when(repository.findAll()).thenReturn(flightList);
                controller.fetchFlightWithSorted();
            } else {
                System.out.println("Data empty: ");
            }
        } catch (Exception e) {
            assertTrue(e instanceof ApplicationException);
        }
    }

    @Test
    public void fetchFlightByIdServiceTest() {
        Flight flight = new Flight();
        flight.setFlightNumber(1001);
        flight.setOrigin(new Origin("Kolkata", "CCU"));
        flight.setDestination(new Destination("Mumbai", "BOM"));
        flight.setDuration("30");
        List<Flight> flights = List.of(flight);

        try {
            Optional<Integer> lightOptional = flights.stream()
                    .map(Flight::getFlightNumber)
                    .min(Comparator.comparing(Function.identity()));

            if(lightOptional.isPresent()){
                int id = lightOptional.get();
                System.out.println("FlightNumber is not empty: "+ id);
                Mockito.when(repository.findById(id)).thenReturn(Mockito.any());
                controller.fetchFlightById(id);
            }else{
                System.out.println("FlightNumber is empty: ");
            }
        } catch (Exception e) {
           assertFalse(e instanceof ApplicationException);
        }
    }
}