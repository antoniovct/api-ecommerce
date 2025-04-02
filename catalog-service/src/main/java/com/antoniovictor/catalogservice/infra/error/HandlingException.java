package com.antoniovictor.catalogservice.infra.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class HandlingException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldsDto>> erroValidacaoRequisicao(MethodArgumentNotValidException e) {
        var body = e.getFieldErrors().stream().map(FieldsDto::new).toList();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> erroEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    public record FieldsDto(
            String field,
            String msg
    ) {
        public FieldsDto(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
