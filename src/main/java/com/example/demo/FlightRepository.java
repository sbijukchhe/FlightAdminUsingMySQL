package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlightRepository extends CrudRepository<Flight, Long> {
    ArrayList<Flight> findByAirlineNameContainingIgnoreCase(String airlineName);
    ArrayList<Flight> findByOriginFromContainingIgnoreCase (String originFrom);
    ArrayList<Flight> findByDestinationToContainingIgnoreCase(String destinationTo);

    ArrayList<Flight> findByAirlineNameContainingIgnoreCaseAndOriginFromContainingIgnoreCaseAndDestinationToContainingIgnoreCase
            (String airlineName, String originFrom, String destinationTo);

    void deleteByAirlineName(String airlineName);
}
