package com.nbody.simulator.serviceimpl;
import com.nbody.simulator.dto.SimulationOutput;
import com.nbody.simulator.dto.SimulationRequest;
import com.nbody.simulator.exception.SimulationException;
import com.nbody.simulator.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

@SpringBootTest
public class SimulationServiceImplTest {

    @Autowired
    private SimulationService simulationService;

    private SimulationRequest validRequest;

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

    @BeforeEach
    public void setup() {
        // Create a valid SimulationRequest with 2 particles
        validRequest = new SimulationRequest();
        validRequest.setXList(Arrays.asList(0.1f, 0.9f));  // Positions in the unit square
        validRequest.setYList(Arrays.asList(0.1f, 0.9f));
        validRequest.setVxList(Arrays.asList(0f, 0f));     // Initial velocities
        validRequest.setVyList(Arrays.asList(0f, 0f));
        validRequest.setMList(Arrays.asList(1.0f, 1.0f));  // Equal masses
        validRequest.setSimulationTime(10.0f);             // Total simulation time
        validRequest.setNumTimesteps(100);                 // Number of timesteps
        validRequest.setGravitationalConstant(6.67430e-11f); // Gravitational constant
    }

    // Test case: valid two-particle simulation
    @Test
    public void testValidTwoParticleSimulation() {
        assertDoesNotThrow(() -> simulationService.simulate(validRequest));
    }

    // Test case: make sure output is correct length
    @Test
    public void testValidLengthOutput() {
        SimulationOutput output = simulationService.simulate(validRequest);
        assertEquals(100, output.getFrameList().size(), "Number of frames should be 100");
    }

    // Test case: invalid request (negative mass)
    @Test
    public void testNegativeMass() {
        validRequest.setMList(Arrays.asList(1.0f, -1.0f));  // One particle with negative mass

        Exception exception = assertThrows(SimulationException.class, () -> {
            simulationService.simulate(validRequest);
        });
        assertEquals(negativeMassMessage, exception.getMessage());
    }

    // Test case: invalid request (coordinates out of bounds)
    @Test
    public void testCoordinatesOutOfBounds() {
        validRequest.setXList(Arrays.asList(1.5f, 0.9f));  // One particle outside the unit square
        validRequest.setYList(Arrays.asList(0.1f, 1.2f));

        Exception exception = assertThrows(SimulationException.class, () -> {
            simulationService.simulate(validRequest);
        });
        assertEquals(invalidCoordinatesMessage, exception.getMessage());
    }

    // Test case: invalid request (zero timesteps)
    @Test
    public void testZeroTimesteps() {
        validRequest.setNumTimesteps(0);  // Invalid number of timesteps

        Exception exception = assertThrows(SimulationException.class, () -> {
            simulationService.simulate(validRequest);
        });
        assertEquals(invalidTimestepsMessage, exception.getMessage());
    }

    // Test case: invalid request (negative simulation time)
    @Test
    public void testNegativeSimulationTime() {
        validRequest.setSimulationTime(-5.0f);  // Invalid negative simulation time

        Exception exception = assertThrows(SimulationException.class, () -> {
            simulationService.simulate(validRequest);
        });
        assertEquals(negativeTimeMessage, exception.getMessage());
    }

    // Test case: invalid request (arrays of different lengths)
    @Test
    public void testArrayLengthMismatch() {
        validRequest.setYList(Arrays.asList(0.1f));  // One array has fewer elements

        Exception exception = assertThrows(SimulationException.class, () -> {
            simulationService.simulate(validRequest);
        });
        assertEquals(invalidArrayLengthMessage, exception.getMessage());
    }
}

