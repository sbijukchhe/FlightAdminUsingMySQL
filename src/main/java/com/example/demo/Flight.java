package com.example.demo;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String airlineName;
    private String originFrom;
    private String destinationTo;
    private double fare;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;

    public Flight() {
    }

    public Flight(String airlineName, String originFrom, String destinationTo, double fare, Date date) {
        this.airlineName = airlineName;
        this.originFrom = originFrom;
        this.destinationTo = destinationTo;
        this.fare = fare;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getOriginFrom() {
        return originFrom;
    }

    public void setOriginFrom(String originFrom) {
        this.originFrom = originFrom;
    }

    public String getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineName='" + airlineName + '\'' +
                ", originFrom='" + originFrom + '\'' +
                ", destinationTo='" + destinationTo + '\'' +
                ", fare=" + fare +
                ", date=" + date +
                '}';
    }
}

