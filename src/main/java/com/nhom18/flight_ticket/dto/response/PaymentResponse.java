package com.nhom18.flight_ticket.dto.response;

import com.nhom18.flight_ticket.core.PaymentMethod;
import com.nhom18.flight_ticket.core.PaymentStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentResponse {
    private int ticket_id;
    private int user_id;
    private BigDecimal amount;
    private PaymentMethod payment_method;
    private PaymentStatus payment_status;
    private Timestamp payment_date;

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }
}
