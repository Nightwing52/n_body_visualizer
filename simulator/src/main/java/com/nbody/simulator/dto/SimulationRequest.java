package com.nbody.simulator.dto;

import java.util.List;

public class SimulationRequest {
    List<Float> x;
    List<Float> y;
    List<Float> vx;
    List<Float> vy;
    List<Float> m;
    Float T;
    Integer M;
    Float G;

    public List<Float> getX() {
        return x;
    }

    public void setX(List<Float> x) {
        this.x = x;
    }

    public List<Float> getY() {
        return y;
    }

    public void setY(List<Float> y) {
        this.y = y;
    }

    public List<Float> getVx() {
        return vx;
    }

    public void setVx(List<Float> vx) {
        this.vx = vx;
    }

    public List<Float> getVy() {
        return vy;
    }

    public void setVy(List<Float> vy) {
        this.vy = vy;
    }

    public List<Float> getM() {
        return m;
    }

    public void setM(Integer m) {
        M = m;
    }

    public Float getG() {
        return G;
    }

    public void setG(Float g) {
        G = g;
    }

    public void setM(List<Float> m) {
        this.m = m;
    }

    public Float getT() {
        return T;
    }

    public void setT(Float t) {
        T = t;
    }

    @Override
    public String toString() {
        return "SimulationRequest{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", m=" + m +
                ", T=" + T +
                ", M=" + M +
                ", G=" + G +
                '}';
    }
}
