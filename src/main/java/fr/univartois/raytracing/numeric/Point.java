/**
 * The Point class represents a triplet of values using a Triplet.
 * This class provides mathematical operations that you can apply on the Points.
 *
 * @author antoine_crauser
 */
package fr.univartois.raytracing.numeric;

public class Point {

    /**
     * This is the triplet representing the point.
     */
    protected final Triplet triplet; //we use an instance of Triplet also in final

    /**
     * This constructor constructs a new Point using a Triplet.
     *
     * @param point The Triplet that defines the coordinates of the point.
     */
    public Point(Triplet point) { //constructor
        this.triplet=point;
    }

    /**
     * Returns the Triplet of the Point.
     *
     * @return The Point.
     */
    public Triplet getTriplet() {//getter
        return this.triplet;
    }


    /**
     * Subtracts two Points and returns the result as a new Vector.
     *
     * @param substract is the Point to subtract from this Point.
     * @return A Vector representing the subtraction between the two points.
     */

    public Vector substraction(Point substract){//we use the substraction from Triplet with an instance of Point, but it returns a Vector
        return new Vector(triplet.substraction(substract.getTriplet()));
    }

    /**
     * Make the scalar Multiplication of triplet on the Point and returns the result as a new Point.
     *
     * @param d is the double to multiply on the Point.
     * @return A Point representing the scalar multiplication of the triplet and the double.
     */

    public Point scalarMultiplication(double d){ // we use the scalar Multiplication from Triplet, but it returns a Point
        return new Point(triplet.scalarMultiplication(d));
    }

    /**
     * Returns a String of the values in the Point as a triplet
     *
     * @return A String representing the string values of this Point.
     */
    @Override
    public String toString() {
        return this.triplet.toString();
    }
}

