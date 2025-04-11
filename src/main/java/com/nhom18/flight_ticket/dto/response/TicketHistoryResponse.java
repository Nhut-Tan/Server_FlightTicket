package com.nhom18.flight_ticket.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketHistoryResponse {
    private int ticketId;
    private String name;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private Timestamp departureTime;
    private String travelClass;
    private BigDecimal price;

    public TicketHistoryResponse(int ticketId, String name, String flightNumber,
            String departureAirport, String arrivalAirport,
            Timestamp departureTime,
            String travelClass, BigDecimal price) {
        this.ticketId = ticketId;
        this.name = name;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.travelClass = travelClass;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
