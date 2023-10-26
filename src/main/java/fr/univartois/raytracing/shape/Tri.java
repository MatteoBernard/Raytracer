package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * This class represents a triangle implementing the IShape interface.
 * defined by three points: pointA, pointB, and pointC.
 */
public class Tri implements IShape {
    // Three points defining the triangle
    Point pointA;
    Point pointB;
    Point pointC;

    /**
     * Create a new Triangle with the given points.
     *
     * @param pointA The first point of the triangle.
     * @param pointB The second point of the triangle.
     * @param pointC The third point of the triangle.
     */
    public Tri(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    /**
     * Get the pointA of the triangle.
     *
     * @return The pointA of the triangle.
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Set the pointA of the triangle.
     *
     * @param pointA The new pointA to set for the triangle.
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    /**
     * Get pointB of the triangle.
     *
     * @return  pointB of the triangle.
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Set pointB of the triangle.
     *
     * @param pointB The new pointB to set for the triangle.
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    /**
     * Get the  pointC of the triangle.
     *
     * @return The pointC of the triangle.
     */
    public Point getPointC() {
        return pointC;
    }

    /**
     * Set  pointC of the triangle.
     *
     * @param pointC The pointC to set for the triangle.
     */
    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    /**
     * Calculates the intersection of a ray represented by a point and a direction vector
     * with a triangle defined by three points in 3D space.
     *
     * @param p The starting point of the ray.
     * @param d The direction vector of the ray.
     * @return The intersection point as a parameterized distance along the ray.
     * @throws UnsupportedOperationException if the intersection point does not satisfy the
     *                                      conditions for being within the triangle.
     */

    @Override
    public double intersect(Point p, Vector d) {
        Vector n = (this.pointB.substraction(this.pointA)).vectorProduct(this.pointC.substraction(this.pointA));
        n = n.norm();
        Plane plane = new Plane(pointA,n);
        double t = plane.intersect(p,d);
        Point P = d.addition(p).scalarMultiplication(t);
        double condA= ((this.pointB.substraction(this.pointA)).vectorProduct(P.substraction(this.pointA))).scalarProduct(n);
        double condB = ((this.pointC.substraction(this.pointB)).vectorProduct(P.substraction(this.pointB))).scalarProduct(n);
        double condC = ((this.pointA.substraction(this.pointC)).vectorProduct(P.substraction(this.pointC))).scalarProduct(n);
        if (condA<0 || condB<0 || condC<0){
            return -1;
        }
        Plane plane2 = new Plane(P,n);
        return plane2.intersect(p,d);
    }
}

