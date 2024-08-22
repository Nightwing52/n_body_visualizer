package com.nbody.simulator.dto;

import java.util.List;

public class SimulationRequest {
    List<Float> xList;
    List<Float> yList;
    List<Float> vxList;
    List<Float> vyList;
    List<Float> mList;
    Float simulationTime;
    Integer numTimesteps;
    Float gravitationalConstant;

    public List<Float> getxList() {
        return xList;
    }

    public void setxList(List<Float> xList) {
        this.xList = xList;
    }

    public List<Float> getyList() {
        return yList;
    }

    public void setyList(List<Float> yList) {
        this.yList = yList;
    }

    public List<Float> getVxList() {
        return vxList;
    }

    public void setVxList(List<Float> vxList) {
        this.vxList = vxList;
    }

    public List<Float> getVyList() {
        return vyList;
    }

    public void setVyList(List<Float> vyList) {
        this.vyList = vyList;
    }

    public List<Float> getmList() {
        return mList;
    }

    public void setmList(List<Float> mList) {
        this.mList = mList;
    }

    public Float getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(Float simulationTime) {
        this.simulationTime = simulationTime;
    }

    public Integer getNumTimesteps() {
        return numTimesteps;
    }

    public void setNumTimesteps(Integer numTimesteps) {
        this.numTimesteps = numTimesteps;
    }

    public Float getGravitationalConstant() {
        return gravitationalConstant;
    }

    public void setGravitationalConstant(Float gravitationalConstant) {
        this.gravitationalConstant = gravitationalConstant;
    }

    @Override
    public String toString() {
        return "SimulationRequest{" +
                "xList=" + xList +
                ", yList=" + yList +
                ", vxList=" + vxList +
                ", vyList=" + vyList +
                ", mList=" + mList +
                ", simulationTime=" + simulationTime +
                ", numTimesteps=" + numTimesteps +
                ", gravitationalConstant=" + gravitationalConstant +
                '}';
    }
}
