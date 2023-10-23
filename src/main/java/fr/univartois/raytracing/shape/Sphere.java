package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;

/**
 * The Sphere class represents a sphere defined by a center point and a radius.
 */
public class Sphere implements IShape {
    // center point of the sphere.
    private Point point;

    // radius of the sphere.
    private double radius;

    /**
     * Constructor for the Sphere class.
     *
     * @param point  center point of the sphere.
     * @param radius radius of the sphere.
     */
    public Sphere(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    /**
     * Get the center point of the sphere.
     *
     * @return center point of the sphere.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Set the center point of the sphere.
     *
     * @param point center point of the sphere.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Get the radius of the sphere.
     *
     * @return radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the radius of the sphere.
     *
     * @param radius radius of the sphere.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
