package com.ahmad.carrental.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponse {
    String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
