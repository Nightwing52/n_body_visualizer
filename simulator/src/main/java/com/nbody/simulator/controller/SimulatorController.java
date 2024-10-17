package com.nbody.simulator.controller;

import com.nbody.simulator.dto.SimulationOutput;
import com.nbody.simulator.dto.SimulationRequest;
import com.nbody.simulator.service.SimulationService;
import com.nbody.simulator.validator.SimulationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/simulator")
public class SimulatorController {
    @Autowired
    SimulationService simulationService;

    @PostMapping(value = "/v1/simulate")
    public ResponseEntity<SimulationOutput> simulate(@RequestBody SimulationRequest request) {
        return ResponseEntity.ok(simulationService.simulate(request));
    }
}
