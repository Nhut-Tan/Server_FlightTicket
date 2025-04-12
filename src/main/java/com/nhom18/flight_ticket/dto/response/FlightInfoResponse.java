package com.nhom18.flight_ticket.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.nhom18.flight_ticket.core.FlightStatus;


public class FlightInfoResponse {
    private int flight_id;
    private String flight_number;
    private int origin_airport;
    private int des_airport;
    private Timestamp departure_time;
    private Timestamp arrival_time;
    private int total_seats;
    private int available_seats;
    private BigDecimal price;
    private FlightStatus status;

    public FlightInfoResponse(int flight_id, String flight_number, int origin_airport, int des_airport,
            Timestamp departure_time, Timestamp arrival_time, int total_seats, int available_seats, BigDecimal price,
            FlightStatus status) {
        this.flight_id = flight_id;
        this.flight_number = flight_number;
        this.origin_airport = origin_airport;
        this.des_airport = des_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
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

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public int getOrigin_airport() {
        return origin_airport;
    }

    public void setOrigin_airport(int origin_airport) {
        this.origin_airport = origin_airport;
    }

    public int getDes_airport() {
        return des_airport;
    }

    public void setDes_airport(int des_airport) {
        this.des_airport = des_airport;
    }

    public Timestamp getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Timestamp departure_time) {
        this.departure_time = departure_time;
    }

    public Timestamp getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Timestamp arrival_time) {
        this.arrival_time = arrival_time;
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
