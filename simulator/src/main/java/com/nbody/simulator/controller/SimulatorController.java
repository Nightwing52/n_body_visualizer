package com.nbody.simulator.controller;

import com.nbody.simulator.dto.Frame;
import com.nbody.simulator.dto.Particle;
import com.nbody.simulator.dto.SimulationOutput;
import com.nbody.simulator.dto.SimulationRequest;
import com.nbody.simulator.validator.SimulationRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/simulator")
public class SimulatorController {
    @PostMapping(value = "/v1/simulate")
    public ResponseEntity<SimulationOutput> simulate(@RequestBody SimulationRequest request) {
        // reflect input for now
        SimulationRequestValidator.validateSimulationRequest(request);
        return ResponseEntity.ok(reflectInput(request));
    }

    private SimulationOutput reflectInput(SimulationRequest request) {
        SimulationOutput simulationOutput = new SimulationOutput();
        List<Frame> frameList = new ArrayList<>();
        Frame frame = new Frame();
        frame.setFrameNumber(0);

        // getting particles
        List<Particle> particleList = new ArrayList<>();
        int N = request.getxList().size();
        for(int i=0; i<N; i++) {
            Particle newParticle = new Particle();
            newParticle.setM(request.getmList().get(i));
            newParticle.setX(request.getxList().get(i));
            newParticle.setY(request.getyList().get(i));
            newParticle.setVx(request.getVxList().get(i));
            newParticle.setVy(request.getVyList().get(i));
            particleList.add(newParticle);
        }

        frame.setParticleList(particleList);
        frameList.add(frame);
        simulationOutput.setFrameList(frameList);

        return simulationOutput;
    }
}
