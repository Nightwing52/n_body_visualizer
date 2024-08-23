package com.nbody.simulator.service;

import com.nbody.simulator.dto.SimulationOutput;
import com.nbody.simulator.dto.SimulationRequest;

public interface SimulationService {
    SimulationOutput simulate(SimulationRequest simulationRequest);
}
