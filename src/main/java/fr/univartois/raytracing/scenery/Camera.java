package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.numeric.Point;

/**
 * The Camera class represents a camera in a ray tracing system. It defines the camera's position (lookFrom),
 * the point it's looking at, the "up" direction, and the field of view (fov).
 */
public class Camera {
    private Point lookFrom;
    private Point lookAt;
    private Point up;
    private Integer fov;

    /**
     * Constructs a new Camera object with the specified parameters.
     *
     * @param lookFrom camera's position in 3D space.
     * @param lookAt   point the camera is looking at.
     * @param up       "up" direction of the camera.
     * @param fov      field of view of the camera.
     */
    public Camera(Point lookFrom, Point lookAt, Point up, Integer fov) {
        this.lookFrom = lookFrom;
        this.lookAt = lookAt;
        this.up = up;
        this.fov = fov;
    }

    /**
     * Get the camera's position.
     *
     * @return position of the camera (lookFrom).
     */
    public Point getLookFrom() {
        return lookFrom;
    }

    /**
     * Set the camera's position.
     *
     * @param lookFrom new position for the camera.
     */
    public void setLookFrom(Point lookFrom) {
        this.lookFrom = lookFrom;
    }

    /**
     * Get the point the camera is looking at.
     *
     * @return point of interest (lookAt).
     */
    public Point getLookAt() {
        return lookAt;
    }

    /**
     * Set the point the camera is looking at.
     *
     * @param lookAt new point of interest for the camera.
     */
    public void setLookAt(Point lookAt) {
        this.lookAt = lookAt;
    }

    /**
     * Get the "up" direction of the camera.
     *
     * @return "up" direction of the camera.
     */
    public Point getUp() {
        return up;
    }

    /**
     * Set the "up" direction of the camera.
     *
     * @param up new "up" direction for the camera.
     */
    public void setUp(Point up) {
        this.up = up;
    }

    /**
     * Get the field of view of the camera.
     *
     * @return field of view (fov) of the camera.
     */
    public Integer getFov() {
        return fov;
    }

    /**
     * Set the field of view of the camera.
     *
     * @param fov new field of view for the camera.
     */
    public void setFov(Integer fov) {
        this.fov = fov;
    }
}
