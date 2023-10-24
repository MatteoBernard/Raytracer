package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * Plane class represents a plane defined by a point and a normal vector.
 */
public class Plane implements IShape{
    // point on the plane.
    private Point point;

    // normal vector of the plane.
    private Vector normal;

    /**
     * Constructor for the Plane class.
     *
     * @param point   point on the plane.
     * @param normal  normal vector of the plane.
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }

    /**
     * Get the point on the plane.
     *
     * @return  point on the plane.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Set point on the plane.
     *
     * @param point  new point on the plane.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Get normal vector of the plane.
     *
     * @return  normal vector of the plane.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Set the normal vector of the plane.
     *
     * @param normal new normal vector of the plane.
     */
    public void setNormal(Vector normal) {
        this.normal = normal;
    }

    /**
     * Calculates the intersection point of a ray represented by a point and a direction vector
     * with a plane defined by a point and a normal vector in 3D space.
     *
     * @param p The starting point of the ray.
     * @param d The direction vector of the ray.
     * @return The intersection point as a parameterized distance along the ray.
     * @throws UnsupportedOperationException if the direction vector is parallel to the plane's normal,
     *                                      causing division by zero.
     */
    @Override
    public double intersect(Point p, Vector d) {
        Vector test = this.point.substraction(p);
        double t = test.scalarProduct(this.normal);
        if (d.scalarProduct(this.normal)==0){
            throw new UnsupportedOperationException("Dividing by zero isn't possible");
        }
        return t/d.scalarProduct(this.normal);
    }
}

