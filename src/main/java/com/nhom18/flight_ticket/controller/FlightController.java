package com.nhom18.flight_ticket.controller;

import com.nhom18.flight_ticket.dto.request.ApiResponse;
import com.nhom18.flight_ticket.dto.request.FlightCreationRequest;
import com.nhom18.flight_ticket.dto.request.FlightUpdateRequest;
import com.nhom18.flight_ticket.dto.response.FlightInfoResponse;
import com.nhom18.flight_ticket.dto.response.FlightSearchResponse;
import com.nhom18.flight_ticket.entity.Flights;
import com.nhom18.flight_ticket.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {
    @Autowired
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(
            @RequestParam String departureId,
            @RequestParam String arrivalId,
            @RequestParam String outboundDate,
            @RequestParam(required = false) String returnDate) {
        List<FlightSearchResponse> flights = flightService.searchFlights(departureId, arrivalId, outboundDate,
                returnDate);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/flights")
    public ApiResponse<List<FlightInfoResponse>> getAllFlight() {
        ApiResponse<List<FlightInfoResponse>> apiResponse = new ApiResponse<>();
        try {
            List<FlightInfoResponse> list = flightService.getAllFlights();
            apiResponse.setResult(list);
            apiResponse.setCode(200);
            apiResponse.setMessage("Get Success");
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error retrieving list flights: " + e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/flight")
    public ApiResponse<Flights> createFlight(@RequestBody FlightCreationRequest request) {
        ApiResponse<Flights> apiResponse = new ApiResponse<>();
        try {
            Flights flight = flightService.createFlight(request);
            apiResponse.setCode(200);
            apiResponse.setResult(flight);
            apiResponse.setMessage("Flight created successfully");
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error creating flight: " + e.getMessage());
        }
        return apiResponse;
    }

    @PutMapping("update/status/{id}")
    public ApiResponse<Flights> updateStatus(@PathVariable("id") int id, @RequestBody FlightUpdateRequest request) {
        ApiResponse<Flights> apiResponse = new ApiResponse<>();
        try {
            Flights flight = flightService.updateStatusFlight(id, request);
            apiResponse.setCode(200);
            apiResponse.setResult(flight);
            apiResponse.setMessage("Status flight updated successfully");
        } catch (Exception e) {
            apiResponse.setCode(500);
            apiResponse.setMessage("Error updating status flight: " + e.getMessage());
        }
        return apiResponse;
    }
}
