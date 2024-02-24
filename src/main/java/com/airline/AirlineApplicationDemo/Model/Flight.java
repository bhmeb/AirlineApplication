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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;

        if (getFlightNumber() != flight.getFlightNumber()) return false;
        if (!getOrigin().equals(flight.getOrigin())) return false;
        if (!getDestination().equals(flight.getDestination())) return false;
        return getDuration().equals(flight.getDuration());
    }

    @Override
    public int hashCode() {
        int result = getFlightNumber();
        result = 31 * result + getOrigin().hashCode();
        result = 31 * result + getDestination().hashCode();
        result = 31 * result + getDuration().hashCode();
        return result;
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
