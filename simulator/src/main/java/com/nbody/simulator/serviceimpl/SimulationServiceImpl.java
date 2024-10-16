package com.nbody.simulator.serviceimpl;

import com.nbody.simulator.dto.*;
import com.nbody.simulator.service.SimulationService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationServiceImpl implements SimulationService {
    private static final Logger logger = LoggerFactory.getLogger(SimulationService.class);

    public SimulationOutput simulate(SimulationRequest request) {
        logger.debug("In simulate: printing request {}", request);
        float h = request.getSimulationTime()/request.getNumTimesteps(); // timestep
        float G = request.getGravitationalConstant();
        List<Frame> frameList = new ArrayList<>();
        List<Particle> particleList = getParticleList(request);
        for(int i=0; i<request.getNumTimesteps(); i++) {
            List<Particle> nextState = calculateStep(particleList, h, G);
            Frame nextFrame = new Frame(i, nextState);
            frameList.add(nextFrame);
            particleList = nextState;
        }
        return new SimulationOutput(frameList);
    }

    // get initial state
    private List<Particle> getParticleList(SimulationRequest request) {
        List<Particle> particleList = new ArrayList<>();
        for(int i = 0; i<request.getXList().size(); i++) {
            Particle particle = new Particle();
            particle.setX(request.getXList().get(i));
            particle.setY(request.getYList().get(i));
            particle.setVx(request.getVxList().get(i));
            particle.setVy(request.getVyList().get(i));
            particle.setM(request.getMList().get(i));
            particleList.add(particle);
        }
        logger.debug("Particle list {}", particleList);
        return particleList;
    }

    /* timestep by one with leapfrog method
        for more information visit: https://www.johndcook.com/blog/2020/07/13/leapfrog-integrator/
     */
    private List<Particle> calculateStep(List<Particle> particleList, float h, float G) {
        float h_squared = (float) Math.pow(h, 2);
        List<Vector2D> forceArray = calculateForce(particleList, G);
        List<Particle> nextState = new ArrayList<>();
        for(int i=0; i<particleList.size(); i++) { // update positions
            Particle particle = particleList.get(i).copy();
            particle.setX(particle.getX() + particle.getVx()*h + 0.5F* forceArray.get(i).getX()*h_squared);
            particle.setY(particle.getY() + particle.getVy()*h + 0.5F* forceArray.get(i).getY()*h_squared);
            nextState.add(particle);
        }

        List<Vector2D> forceArrayNext = calculateForce(nextState, G); // calculate next force
        for(int i=0; i< particleList.size(); i++) { // update velocities
            Particle particle = nextState.get(i);
            particle.setVx(particle.getVx() + 0.5F*(forceArray.get(i).getX()+forceArrayNext.get(i).getX())*h);
            particle.setVy(particle.getVy() + 0.5F*(forceArray.get(i).getY()+forceArrayNext.get(i).getY())*h);
        }

        return nextState;
    }

    // get force for each particle with x and y component
    private List<Vector2D> calculateForce(List<Particle> particleList, float G) {
        List<Vector2D> forceArray = new ArrayList<>();
        for(int i=0; i< particleList.size(); i++) {
            Particle particle1 = particleList.get(i);
            Vector2D force = new Vector2D(0.0F, 0.0F);
            Vector2D position1 = new Vector2D(particle1.getX(), particle1.getY());
            for(int j=0; j<particleList.size(); j++) { // brute force for now
                if(i == j) // same particle
                    continue;
                Particle particle2 = particleList.get(j);
                Vector2D position2 = new Vector2D(particle2.getX(), particle2.getY());
                Vector2D r12 = Vector2D.subtract(position1, position2);
                float mag = r12.mag();
                float Fx = -1.0F*G*particle1.getM()*particle2.getM()*r12.getX()/((float) Math.pow(mag, 3));
                float Fy = -1.0F*G*particle1.getM()*particle2.getM()*r12.getY()/((float) Math.pow(mag, 3));
                force = Vector2D.add(force, new Vector2D(Fx, Fy));
            }
            forceArray.add(force);
        }
        logger.debug("Force array {}", forceArray);
        return forceArray;
    }


}
