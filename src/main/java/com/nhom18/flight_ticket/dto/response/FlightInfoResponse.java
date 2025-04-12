package com.nhom18.flight_ticket.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.nhom18.flight_ticket.core.FlightStatus;
import com.nhom18.flight_ticket.entity.Airports;

public class FlightInfoResponse {
    private int flight_id;
    private String flightNumber;
    private int origin_airport;
    private int des_airport;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private int total_seats;
    private int available_seats;
    private BigDecimal price;
    private FlightStatus status;

    public FlightInfoResponse(int flight_id, String flightNumber, int origin_airport, int des_airport,
            Timestamp departureTime, Timestamp arrivalTime, int total_seats, int available_seats, BigDecimal price,
            FlightStatus status) {
        this.flight_id = flight_id;
        this.flightNumber = flightNumber;
        this.origin_airport = origin_airport;
        this.des_airport = des_airport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.total_seats = total_seats;
        this.available_seats = available_seats;
        this.price = price;
        this.status = status;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getOriginAirport() {
        return origin_airport;
    }

    public void setOriginAirport(int origin_airport) {
        this.origin_airport = origin_airport;
    }

    public int getDestinationAirport() {
        return des_airport;
    }

    public void setDestinationAirport(int des_airport) {
        this.des_airport = des_airport;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

}
