package com.airline.AirlineApplicationDemo;

import com.airline.AirlineApplicationDemo.Model.Destination;
import com.airline.AirlineApplicationDemo.Model.Flight;
import com.airline.AirlineApplicationDemo.Model.Origin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetJson {

    public static void main(String[] args) {
        ObjectMapper obj = new ObjectMapper();
        Destination flight = new Destination();
        try{
            String JsonStr = obj.writeValueAsString(flight);
            System.out.println("Object: "+ JsonStr);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
