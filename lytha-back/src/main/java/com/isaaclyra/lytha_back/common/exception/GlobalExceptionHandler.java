package com.isaaclyra.lytha_back.common.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException exception) {
        List<String> erros = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return createResponse(HttpStatus.BAD_REQUEST, "Erro de validação nos campos.", erros);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> erros = exception.getConstraintViolations()
                .stream()
                .map(err -> err.getPropertyPath() + ": " + err.getMessage())
                .toList();

        return createResponse(HttpStatus.BAD_REQUEST, "Erro de validação em parâmetros.", erros);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOutros(Exception ex) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", List.of(ex.getMessage()));
    }

    private ResponseEntity<Map<String, Object>> createResponse(HttpStatus status, String message) {
        return createResponse(status, message, null);
    }


    private ResponseEntity<Map<String, Object>> createResponse(HttpStatus status, String message, List<String> details) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("mensagem", message);
        if(details != null && !details.isEmpty()){
            body.put("erros", details);
        }

        return ResponseEntity.status(status).body(body);
    }
}
