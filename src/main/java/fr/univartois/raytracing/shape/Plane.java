package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Color;
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

    private Color diffuse, specular;
    private int shininess;
    /**
     * Constructor for the Plane class.
     *
     * @param point   point on the plane.
     * @param normal  normal vector of the plane.
     */
    public Plane(Point point, Vector normal, Color diffuse, Color specular, int shininess) {
        this.point = point;
        this.normal = normal;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
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
     * Retrieves the diffuse color of the shape, which represents its surface color.
     *
     * @return The diffuse color of the shape.
     */
    @Override
    public Color getDiffuse() {
        return diffuse;
    }

    /**
     * Retrieves the specular color of the shape, which represents its highlight color.
     *
     * @return The specular color of the shape.
     */
    @Override
    public Color getSpecular() {
        return specular;
    }
    /**
     * Retrieves the shininess of the shape's surface, affecting the intensity of specular highlights.
     *
     * @return The shininess of the shape.
     */
    @Override
    public int getShininess() {
        return shininess;
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
        t=t/d.scalarProduct(this.normal);
        return t;
    }
    /**
     * Retrieves the center point of the plane.
     *
     * @return The center point of the shape, or null if not applicable.
     */
    @Override
    public Point getCenter() {
        return null;
    }
}

