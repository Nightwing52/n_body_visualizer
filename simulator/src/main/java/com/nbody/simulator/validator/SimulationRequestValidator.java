package com.nbody.simulator.validator;

import com.nbody.simulator.dto.SimulationRequest;
import com.nbody.simulator.exception.SimulationException;

public class SimulationRequestValidator {
    public static void validateSimulationRequest(SimulationRequest simulationRequest) {
        // check all arrays are the same length
        int N = simulationRequest.getxList().size();
        if(simulationRequest.getyList().size() != N ||
            simulationRequest.getVxList().size() != N || simulationRequest.getVyList().size() != N ||
            simulationRequest.getmList().size() != N)
            throw new SimulationException("Not all arrays are the same length.");

        // check simulation parameters
        if(simulationRequest.getSimulationTime() <= 0.0)
            throw new SimulationException("Time must be positive.");

        if(simulationRequest.getNumTimesteps() <= 0)
            throw new SimulationException("Number of timesteps must be positive.");

        if(simulationRequest.getGravitationalConstant() <= 0)
            throw new SimulationException("Gravitational constant must be positive.");

        // check xList and yList is in [0, 1] and mass > 0
        for(int i=0; i<N; i++) {
            float x = simulationRequest.getxList().get(i);
            float y = simulationRequest.getyList().get(i);

            if(x < 0 || y < 0 || x > 1.0 || y > 1.0)
                throw new SimulationException("Particles are not in the unit square.");

            if(simulationRequest.getmList().get(i) <= 0.0)
                throw new SimulationException("Particles must have positive mass.");
        }

        // check mass
    }
}
