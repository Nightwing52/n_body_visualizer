package com.nbody.simulator.dto;

public class Particle {
    Float x;
    Float y;
    Float vx;
    Float vy;
    Float m;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getVx() {
        return vx;
    }

    public void setVx(Float vx) {
        this.vx = vx;
    }

    public Float getVy() {
        return vy;
    }

    public void setVy(Float vy) {
        this.vy = vy;
    }

    public Float getM() {
        return m;
    }

    public void setM(Float m) {
        this.m = m;
    }

    public Particle copy() {
        Particle particleCopy = new Particle();
        particleCopy.setM(this.m);
        particleCopy.setX(this.x);
        particleCopy.setY(this.y);
        particleCopy.setVx(this.vx);
        particleCopy.setVy(this.vy);
        return particleCopy;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", m=" + m +
                '}';
    }
}
