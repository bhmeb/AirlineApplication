package com.airline.AirlineApplicationDemo.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Origin {

    private String orgAirport;
    private String orgCode;

    public Origin(){

    }
    public Origin(String orgAirport, String orgCode) {
        this.orgAirport = orgAirport;
        this.orgCode = orgCode;
    }

    public String getOrgAirport() {
        return orgAirport;
    }

    public void setOrgAirport(String orgAirport) {
        this.orgAirport = orgAirport;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
