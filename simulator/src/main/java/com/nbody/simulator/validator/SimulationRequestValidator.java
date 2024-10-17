package com.nbody.simulator.validator;

import com.nbody.simulator.dto.SimulationRequest;
import com.nbody.simulator.exception.SimulationException;
import org.springframework.beans.factory.annotation.Value;

public class SimulationRequestValidator {
    @Value("${validation.simulation.negative.mass}")
    private static String negativeMassMessage;

    @Value("${validation.simulation.negative.time}")
    private static String negativeTimeMessage;

    @Value("${validation.simulation.negative.gravitationalConstant}")
    private static String negativeGravitationalConstantMessage;

    @Value("${validation.simulation.invalid.coordinates}")
    private static String invalidCoordinatesMessage;

    @Value("${validation.simulation.invalid.timesteps}")
    private static String invalidTimestepsMessage;

    @Value("${validation.simulation.invalid.arrayLength}")
    private static String invalidArrayLengthMessage;

    public static void validateSimulationRequest(SimulationRequest simulationRequest) {
        // check all arrays are the same length
        int N = simulationRequest.getXList().size();
        if(simulationRequest.getYList().size() != N ||
            simulationRequest.getVxList().size() != N || simulationRequest.getVyList().size() != N ||
            simulationRequest.getMList().size() != N)
            throw new SimulationException(invalidArrayLengthMessage);

        // check simulation parameters
        if(simulationRequest.getSimulationTime() <= 0.0)
            throw new SimulationException(negativeTimeMessage);

        if(simulationRequest.getNumTimesteps() <= 0)
            throw new SimulationException(invalidTimestepsMessage);

        if(simulationRequest.getGravitationalConstant() <= 0)
            throw new SimulationException(negativeGravitationalConstantMessage);

        // check xList and yList is in [0, 1] and mass > 0
        for(int i=0; i<N; i++) {
            float x = simulationRequest.getXList().get(i);
            float y = simulationRequest.getYList().get(i);

            if(x < 0 || y < 0 || x > 1.0 || y > 1.0)
                throw new SimulationException(invalidCoordinatesMessage);

            if(simulationRequest.getMList().get(i) <= 0.0)
                throw new SimulationException(negativeMassMessage);
        }

        // check mass
    }
}
