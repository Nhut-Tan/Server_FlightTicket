package com.nhom18.flight_ticket.repository;

import com.nhom18.flight_ticket.dto.response.TicketHistoryResponse;
import com.nhom18.flight_ticket.entity.Tickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Integer> {
    @Query("SELECT new com.nhom18.flight_ticket.dto.response.TicketHistoryResponse(" +
            "t.ticket_id,t.name, f.flight_number,oa.city, " +
            "da.city,f.departure_time, fc.class_name, t.price) " +
            "FROM Tickets t " +
            "JOIN t.flight f " +
            "JOIN f.origin_airport oa " +
            "JOIN f.des_airport da " +
            "JOIN t.flightclass fc " +
            "WHERE t.account.account_id = :account_id")
    List<TicketHistoryResponse> getTicketHistory(@Param("account_id") int accountId);

}
