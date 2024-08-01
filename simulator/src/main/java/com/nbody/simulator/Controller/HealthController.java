package com.nbody.simulator.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {
    @GetMapping(value = "/actuator/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health check passed.");
    }
}
