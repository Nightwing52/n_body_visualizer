package com.nbody.simulator.dto;

import java.util.List;

public class Frame {
    Integer frameNumber;
    List<Particle> particleList;

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public List<Particle> getParticleList() {
        return particleList;
    }

    public void setParticleList(List<Particle> particleList) {
        this.particleList = particleList;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "frameNumber=" + frameNumber +
                ", particleList=" + particleList +
                '}';
    }
}
