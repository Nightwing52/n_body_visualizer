package com.nbody.simulator.dto;

import java.util.List;

public class SimulationOutput {
    List<Frame> frameList;

    public SimulationOutput(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public void setFrameList(List<Frame> frameList) {
        this.frameList = frameList;
    }

    @Override
    public String toString() {
        return "SimulationOutput{" +
                "frameList=" + frameList +
                '}';
    }
}
