package com.airline.AirlineApplicationDemo.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Destination {

    private String destAirport;
    private String destCode;

    public Destination(){}
    public Destination(String destAirport, String destCode) {
        this.destAirport = destAirport;
        this.destCode = destCode;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public String getDestCode() {
        return destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }
}
