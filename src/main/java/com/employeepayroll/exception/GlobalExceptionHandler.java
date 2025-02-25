package com.employeepayroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String>errors = new HashMap<>();
        List<FieldError>fieldErrors = ex.getBindingResult().getFieldErrors();

        for(int i=0; i< fieldErrors.size() ;i++){
            String field =fieldErrors.get(i).getField();
            String message= fieldErrors.get(i).getDefaultMessage();
            errors.put(field, message);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
