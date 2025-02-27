package com.employeepayroll.exception;
import com.employeepayroll.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("Invalid method argument");
        // Extract field validation errors
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        // Create a ResponseDTO with validation errors
        ResponseDTO response = new ResponseDTO("Validation failed", HttpStatus.BAD_REQUEST.value(), errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle Employee Not Found Exception
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        log.error("No employee found with this id");
        ResponseDTO response = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle general runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleRuntimeException(RuntimeException ex) {
        log.error("Rum time Exception");
        ResponseDTO response = new ResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
