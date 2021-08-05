package com.ahmad.carrental.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDTO {
    String message;
    LocalDateTime time;

    public ResponseDTO(String message) {
        this.time = LocalDateTime.now();
        this.message = message;
    }
}
