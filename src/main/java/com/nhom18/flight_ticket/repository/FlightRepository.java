package com.nhom18.flight_ticket.repository;

import com.nhom18.flight_ticket.dto.response.FlightInfoResponse;
import com.nhom18.flight_ticket.entity.Flights;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Integer> {

    @Query("SELECT new com.nhom18.flight_ticket.dto.response.FlightInfoResponse(" +
            "f.flight_id,f.flight_number,oa.airport_id,da.airport_id, f.departure_time, f.arrival_time,f.total_seats,f.available_seats,f.price, f.status) "
            +
            "FROM Flights f " +
            "JOIN f.origin_airport oa " +
            "JOIN f.des_airport da")
    List<FlightInfoResponse> getAllFlightInfo();
}
