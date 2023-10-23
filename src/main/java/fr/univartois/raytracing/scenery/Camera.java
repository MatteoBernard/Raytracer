package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.numeric.Point;

public class Camera {
    Point lookFrom;
    Point lookAt;
    Point up;
    Integer fov;

    public Camera(Point lookFrom, Point lookAt, Point up, Integer fov) {
        this.lookFrom = lookFrom;
        this.lookAt = lookAt;
        this.up = up;
        this.fov = fov;
    }

    public Point getLookFrom() {
        return lookFrom;
    }

    public void setLookFrom(Point lookFrom) {
        this.lookFrom = lookFrom;
    }

    public Point getLookAt() {
        return lookAt;
    }

    public void setLookAt(Point lookAt) {
        this.lookAt = lookAt;
    }

    public Point getUp() {
        return up;
    }

    public void setUp(Point up) {
        this.up = up;
    }

    public Integer getFov() {
        return fov;
    }

    public void setFov(Integer fov) {
        this.fov = fov;
    }
}
