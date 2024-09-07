package com.nbody.simulator.dto;

public class Vector2D {
    float x;
    float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static Vector2D add(Vector2D vector1, Vector2D vector2) {
        return new Vector2D(vector1.getX()+vector2.getX(), vector1.getY()+ vector2.getY());
    }

    public static Vector2D subtract(Vector2D vector1, Vector2D vector2) {
        return new Vector2D(vector1.getX()-vector2.getX(), vector1.getY()-vector2.getY());
    }

    public float mag() {
        return (float) Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
