package com.nhom18.flight_ticket.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom18.flight_ticket.core.FlightStatus;
import com.nhom18.flight_ticket.dto.request.FlightCreationRequest;
import com.nhom18.flight_ticket.dto.request.FlightUpdateRequest;
import com.nhom18.flight_ticket.dto.response.FlightSearchResponse;
import com.nhom18.flight_ticket.entity.Airlines;
import com.nhom18.flight_ticket.entity.Airports;
import com.nhom18.flight_ticket.entity.Flights;
import com.nhom18.flight_ticket.repository.AirlineRepository;
import com.nhom18.flight_ticket.repository.AirportRepository;
import com.nhom18.flight_ticket.repository.FlightRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final String SERPAPI_KEY = "0af9d871d47dd3c85ab8a5f44b4bd1f539ca1180962168a5150eef13465fa050";
    private static final String SERPAPI_URL = "https://serpapi.com/search.json";

    public FlightService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<FlightSearchResponse> searchFlights(String departureId, String arrivalId, String outboundDate,
            String returnDate) {
        List<FlightSearchResponse> results = new ArrayList<>();

        try {
            // Xác định loại chuyến bay
            String type = (returnDate != null && !returnDate.isEmpty()) ? "1" : "2";

            // ✅ Tạo URL với type chính xác
            String apiUrl = SERPAPI_URL + "?engine=google_flights"
                    + "&departure_id=" + departureId
                    + "&arrival_id=" + arrivalId
                    + "&outbound_date=" + outboundDate
                    + (returnDate != null && !returnDate.isEmpty() ? "&return_date=" + returnDate : "")
                    + "&type=" + type
                    + "&api_key=" + SERPAPI_KEY;

            // ✅ Gửi request đến SerpAPI
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            String jsonResponse = response.getBody();

            // 🟢 In JSON gốc để kiểm tra phản hồi API
            System.out.println("JSON Response: " + jsonResponse);

            // ✅ Xử lý JSON response
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode bestFlightsNode = rootNode.path("best_flights");

            for (JsonNode bestFlight : bestFlightsNode) {
                JsonNode flightsArray = bestFlight.path("flights"); // Lấy danh sách flights

                for (JsonNode flight : flightsArray) {
                    FlightSearchResponse flightResponse = new FlightSearchResponse();

                    flightResponse.setAirlineLogo(flight.path("airline_logo").asText());

                    // Lấy thông tin sân bay đi
                    JsonNode departureNode = flight.path("departure_airport");
                    flightResponse.setDepartureAirportId(departureNode.path("id").asText());
                    flightResponse.setDepartureTime(departureNode.path("time").asText());

                    // Lấy thông tin sân bay đến
                    JsonNode arrivalNode = flight.path("arrival_airport");
                    flightResponse.setArrivalAirportId(arrivalNode.path("id").asText());
                    flightResponse.setArrivalTime(arrivalNode.path("time").asText());
                    // Số hiệu chuyến bay
                    flightResponse.setFlight_number(flight.path("flight_number").asText());
                    // Thời gian bay
                    flightResponse.setDuration(flight.path("duration").asInt());
                    // Hạng ghế
                    flightResponse.setTravelClass(flight.path("travel_class").asText());
                    // Giá vé
                    flightResponse.setPrice(bestFlight.path("price").asDouble());

                    results.add(flightResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flights createFlight(FlightCreationRequest request) {
        Flights flight = new Flights();
        Airlines airline = airlineRepository.findById(request.getAirline_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Airline Id"));
        Airports originAirport = airportRepository.findById(request.getOrigin_airport())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Origin AirPort Id"));
        Airports desAirport = airportRepository.findById(request.getOrigin_airport())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Des Airport Id"));
        flight.setAirline(airline);
        flight.setFlight_number(request.getFlight_number());
        flight.setOrigin_airport(originAirport);
        flight.setDes_airport(desAirport);
        flight.setArrival_time(request.getArrival_time());
        flight.setDeparture_time(request.getDeparture_time());
        flight.setTotal_seats(80);
        flight.setAvailable_seats(80);
        flight.setPrice(request.getPrice());
        flight.setStatus(FlightStatus.SCHEDULED);
        flightRepository.save(flight);
        return flight;
    }

    public Flights updateStatusFlight(int id, FlightUpdateRequest request) {
        Flights flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Flight Id"));
        flight.setStatus(request.getStatus());
        return flightRepository.save(flight);
    }

    public Flights updateIncreaseSeat(Flights flight) {
        flight.setAvailable_seats(flight.getAvailable_seats() + 1);
        if (flight.getStatus() == FlightStatus.FULLY_BOOKED) {
            flight.setStatus(FlightStatus.SCHEDULED);
        }
        return flightRepository.save(flight);
    }

    public Flights updateDecreaseSeat(Flights flight) {
        flight.setAvailable_seats(flight.getAvailable_seats() - 1);
        if (flight.getStatus() == FlightStatus.SCHEDULED && flight.getAvailable_seats() == 0) {
            flight.setStatus(FlightStatus.FULLY_BOOKED);
        }
        return flightRepository.save(flight);
    }
}
