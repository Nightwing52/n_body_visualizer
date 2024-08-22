package com.nbody.simulator.controller;

import com.nbody.simulator.dto.Frame;
import com.nbody.simulator.dto.Particle;
import com.nbody.simulator.dto.SimulationOutput;
import com.nbody.simulator.dto.SimulationRequest;
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
        return ResponseEntity.ok(reflectInput(request));
    }

    private SimulationOutput reflectInput(SimulationRequest request) {
        SimulationOutput simulationOutput = new SimulationOutput();
        List<Frame> frameList = new ArrayList<>();
        Frame frame = new Frame();
        frame.setFrameNumber(0);

        // getting particles
        List<Particle> particleList = new ArrayList<>();
        int N = request.getX().size();
        for(int i=0; i<N; i++) {
            Particle newParticle = new Particle();
            newParticle.setM(request.getM().get(i));
            newParticle.setX(request.getX().get(i));
            newParticle.setY(request.getY().get(i));
            newParticle.setVx(request.getVx().get(i));
            newParticle.setVy(request.getVy().get(i));
        }

        frame.setParticleList(particleList);
        simulationOutput.setFrameList(frameList);

        return simulationOutput;
    }
}
