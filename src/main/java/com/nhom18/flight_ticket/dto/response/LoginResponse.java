package com.nhom18.flight_ticket.dto.response;

import com.nhom18.flight_ticket.core.Role;

public class LoginResponse {
    private int account_id;
    private Role role;

    public LoginResponse(int account_id, Role role) {
        this.account_id = account_id;
        this.role = role;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
