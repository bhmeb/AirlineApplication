package com.airline.AirlineApplicationDemo.Controller;

import com.airline.AirlineApplicationDemo.Exception.ApplicationException;
import com.airline.AirlineApplicationDemo.Model.Destination;
import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Model.Origin;
import com.airline.AirlineApplicationDemo.Repository.FlightRepository;
import com.airline.AirlineApplicationDemo.Service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ControllerTest {

    @InjectMocks
    FlightController controller;
    @Mock
    FlightService service;
    @Mock
    FlightRepository repository;

    @Mock
    Flight flight,flight1;

    @Before
    public void init(){
        flight = new Flight();
        flight.setFlightNumber(1001);
        flight.setOrigin(new Origin("Kolkata", "CCU"));
        flight.setDestination(new Destination("Mumbai", "BOM"));
        flight.setDuration("60");

        flight1 = new Flight();
        flight1.setFlightNumber(1002);
        flight1.setOrigin(new Origin("Mumbai", "BOM"));
        flight1.setDestination(new Destination("Bhubaneswar", "BBI"));
        flight1.setDuration("50");
    }

    @Test
    public void dataValidator(){
        assertFalse(flight.equals(flight1));
    }
    @Test
    public void addFlightTest() {
        try {
            List<Flight> res = Arrays.asList(flight).stream()
                    .collect(Collectors.toList());

            if (res!=null) {
                System.out.println("Data not empty: " + res);
                Mockito.when(service.addFlight(flight)).thenReturn(flight);
                controller.addFlight(flight);
            }
        } catch (Exception e) {
            assertFalse(e instanceof ApplicationException);
        }
    }

    @Test
    public void fetchFlightTest() {
        List<Flight> flightList = Arrays.asList(flight, flight1);
        try {
            Optional<Flight> mockData = flightList.stream()
                    .min(Comparator.comparing(Flight::getDuration));

            if (mockData.isPresent()) {
                System.out.println("Data not empty: " + mockData.get());
                Mockito.when(service.fetchFlightWithSorted()).thenReturn(flightList);
                controller.fetchFlightWithSorted();
            } else {
                System.out.println("Data empty: ");
            }
        } catch (Exception e) {
            assertTrue(e instanceof ApplicationException);
        }
    }

    @Test
    public void fetchFlightByIdTest() {
        try {
            Optional<Integer> lightOptional = Arrays.asList(flight).stream()
                    .map(Flight::getFlightNumber)
                    .min(Comparator.comparing(Function.identity()));

            if(lightOptional.isPresent()){
                int id = lightOptional.get();
                System.out.println("FlightNumber is not empty: "+ id);
                Mockito.when(service.fetchFlightById(id)).thenReturn(Mockito.any());
                controller.fetchFlightById(id);
            }else{
                System.out.println("FlightNumber is empty: ");
            }
        } catch (Exception e) {
            assertFalse(e instanceof ApplicationException);
        }
    }
}
