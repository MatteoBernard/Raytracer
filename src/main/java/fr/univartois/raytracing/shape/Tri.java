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

    @Override
    public double intersect(Point p, Vector d) {
        return 0;
    }
}

