package br.com.jeniferocha.quickcart.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    // qnd o sistema lançar EstoqueInsuficienteException, este método responde no postman
    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<StandardError> estoqueInsuficiente(EstoqueInsuficienteException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Erro 400

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Regra de Negócio - Estoque Insuficiente",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}