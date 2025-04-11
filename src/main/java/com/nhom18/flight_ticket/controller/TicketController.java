package com.nhom18.flight_ticket.controller;

import com.nhom18.flight_ticket.dto.request.ApiResponse;
import com.nhom18.flight_ticket.dto.request.TicketCreationRequest;
import com.nhom18.flight_ticket.dto.request.TicketUpdateRequest;
import com.nhom18.flight_ticket.dto.response.TicketHistoryResponse;
import com.nhom18.flight_ticket.entity.Tickets;
import com.nhom18.flight_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/user/booking")
    public ApiResponse<Tickets> createTicket(@RequestBody TicketCreationRequest request) {
        ApiResponse<Tickets> apiResponse = new ApiResponse<>();
        try {
            Tickets ticket = ticketService.createTicket(request);
            if (ticket != null) {
                apiResponse.setCode(200);
                apiResponse.setResult(ticket);
                apiResponse.setMessage("Ticket booked successfully");
            } else {
                apiResponse.setCode(200);
                apiResponse.setMessage("The plane is full");
            }
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error booking ticket: " + e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/historyTicket/{account_id}")
    public ApiResponse<List<TicketHistoryResponse>> getTicketHistory(@PathVariable("account_id") int accountId) {
        ApiResponse<List<TicketHistoryResponse>> apiResponse = new ApiResponse<>();
        try {
            List<TicketHistoryResponse> history = ticketService.getTicketHistoryByAccountId(accountId);
            if (!history.isEmpty() ) {
                apiResponse.setResult(history);
                apiResponse.setCode(200);
                apiResponse.setMessage("Lịch sử vé đã đặt");
            } else {
                apiResponse.setCode(200);
                apiResponse.setMessage("Bạn chưa đặt vé nào");
            }
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Không tồn tại vé" + e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/admin/bookings")
    public ApiResponse<List<Tickets>> getAllTickets() {
        ApiResponse<List<Tickets>> apiResponse = new ApiResponse<>();
        try {
            List<Tickets> list = ticketService.getAllTickets();
            if (list != null) {
                apiResponse.setResult(list);
                apiResponse.setCode(200);
                apiResponse.setMessage("Get Success");
            } else {
                apiResponse.setCode(404);
                apiResponse.setMessage("List ticket not found");
            }
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error retrieving list ticket: " + e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/admin/bookings/{booking_id}")
    public ApiResponse<Tickets> getById(@PathVariable("booking_id") int id) {
        ApiResponse<Tickets> apiResponse = new ApiResponse<>();
        try {
            Tickets ticket = ticketService.getById(id);
            if (ticket != null) {
                apiResponse.setResult(ticket);
                apiResponse.setCode(200);
                apiResponse.setMessage("Get Success");
            } else {
                apiResponse.setCode(404);
                apiResponse.setMessage("Ticket not found");
            }
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error retrieving ticket: " + e.getMessage());
        }
        return apiResponse;
    }

    @PutMapping("admin/bookings/{booking_id}/status")
    public ApiResponse<Tickets> updateStatusTicket(@PathVariable("booking_id") int id,
            @RequestBody TicketUpdateRequest request) {
        ApiResponse<Tickets> apiResponse = new ApiResponse<>();
        try {
            Tickets updateTicket = ticketService.getById(id);
            if (updateTicket != null) {
                updateTicket = ticketService.updateStatusTicket(id, request);
                apiResponse.setResult(updateTicket);
                apiResponse.setCode(200);
                apiResponse.setMessage("Status update successfully");
            } else {
                apiResponse.setCode(404);
                apiResponse.setMessage("Ticket not found");
            }
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error updating status: " + e.getMessage());
        }
        return apiResponse;
    }

    @PutMapping("user/cancel/{id}")
    public ApiResponse<String> cancelTicket(@PathVariable("id") int id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            ticketService.cancelTicket(id);
            apiResponse.setCode(200);
            apiResponse.setMessage("Ticket has been canceled successfully");
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error canceling ticket: " + e.getMessage());
        }
        return apiResponse;
    }
}