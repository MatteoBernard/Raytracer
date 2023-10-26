package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

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
    @Override
    public Point getCenter() {
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

    public double intersect (Point o, Vector d) {

        double a = 1;
        double b = ((o.substraction(point).scalarMultiplication(2)).scalarProduct(d));
        double c = ((o.substraction(point)).scalarProduct(o.substraction(point)))-(radius*radius);

        double delta = (b*b)-(4*a*c);
        double t;
        if (delta == 0) {
            t = ((b*-1)/(2*a));

            return t;
        }
        else {
            t = (-b + sqrt(delta))/(2*a);
            if (t>=0) {
                return t;
            }
            else {
                t = (-b - sqrt(delta)) / (2 * a);
                if (t >= 0) return t;
            }
        }
        return -1;
    }
}
