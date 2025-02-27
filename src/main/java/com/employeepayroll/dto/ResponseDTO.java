package com.employeepayroll.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
@Getter
@Setter
public class ResponseDTO {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ResponseDTO(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public ResponseDTO(String message, int status, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }
}
