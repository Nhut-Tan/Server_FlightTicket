package com.nhom18.flight_ticket.controller;


import com.nhom18.flight_ticket.dto.request.ApiResponse;
import com.nhom18.flight_ticket.dto.request.PaymentCreationRequest;
import com.nhom18.flight_ticket.entity.Payments;
import com.nhom18.flight_ticket.entity.Tickets;
import com.nhom18.flight_ticket.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService vnPayService;

    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    @PostMapping("/submitOrder")
    public String submidOrder(HttpServletRequest request){
        String vnpayUrl = vnPayService.createOrder(request);
        System.out.println(vnpayUrl);
        return "redirect: " + vnpayUrl;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int paymentStatus = vnPayService.orderReturn(request);
        if (paymentStatus == 1)
            response.sendRedirect("https://flyeasyticketsvn.netlify.app/CheckOut");
        return null;
    }

    @PostMapping("/createPayment")
    public ApiResponse<Payments> createPayment (@RequestBody PaymentCreationRequest request){
        ApiResponse<Payments> apiResponse = new ApiResponse<>();
        try {
            Payments payments = vnPayService.createPayment(request);
            if (payments != null) {
                apiResponse.setCode(200);
                apiResponse.setResult(payments);
                apiResponse.setMessage("Payment successfully");
            }
        } catch (Exception e){
            apiResponse.setCode(500);
            apiResponse.setMessage("Error insert payment: "+ e.getMessage());
        }
        return apiResponse;
    }
}
