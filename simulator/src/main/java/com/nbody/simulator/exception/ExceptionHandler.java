package com.nbody.simulator.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = SimulationException.class)
    public ResponseEntity<String> simulationExceptionHandler(SimulationException simulationException) {
        return ResponseEntity.badRequest().body(simulationException.getMessage());
    }
}
