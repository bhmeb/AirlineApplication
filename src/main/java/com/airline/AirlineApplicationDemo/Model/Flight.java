package com.airline.AirlineApplicationDemo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    private int flightNumber;

    private Origin origin;

    private Destination destination;
    private String duration;

    public Flight(){

    }

    public Flight(int flightNumber, Origin origin, Destination destination, String duration) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", origin=" + origin +
                ", destination=" + destination +
                ", duration='" + duration + '\'' +
                '}';
    }
}
