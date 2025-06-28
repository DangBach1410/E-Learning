package com.example.elearning.exception;

import com.example.elearning.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> argException(MethodArgumentNotValidException exception) {
        List<ApiResponseDTO> result = new ArrayList<>();

        for (Object object: exception.getBindingResult().getFieldErrors().toArray()) {
            FieldError fieldError = (FieldError) object;

            ApiResponseDTO response = new ApiResponseDTO();
            response.setMessage(fieldError.getDefaultMessage());
            response.setCode(1000 + fieldError.getField().hashCode() % 1000);

            result.add(response);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        ApiResponseDTO response = new ApiResponseDTO();
        response.setMessage(ex.getReason());
        response.setCode(ex.getStatusCode().value());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }
}
