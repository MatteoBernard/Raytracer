/**
 * The Vector class represents a triplet of coordinates using a Triplet.
 * This class provides mathematical operations that you can apply on the Vector.
 *
 * @author antoine_crauser
 */
package fr.univartois.raytracing.numeric;

public class Vector {
    /**
     * This is the triplet representing the Vector.
     */

    protected final Triplet triplet;
    /**
     * This constructor constructs a new Vector using a Triplet.
     *
     * @param vector The Triplet that defines the coordinates of the Vector.
     */

    public Vector(Triplet vector) {
        this.triplet = vector;
    }
    /**
     * Returns the Triplet of the Vector.
     *
     * @return The Vector.
     */


    public Triplet getTriplet() {
        return this.triplet;
    }
    /**
     * Make an addition between a Vector and a Point and returns the result as a new Point.
     *
     * @param add is the Point given to add to this Vector.
     * @return A new Point representing the addition of the Vector and the Point add.
     */

    public Point addition(Point add) {
        return new Point(triplet.addition(add.getTriplet()));
    }
    /**
     * Make an addition between a Vector and another Vector and returns the result as a new Vector.
     *
     * @param add is the Vector given to add to this Vector.
     * @return A new Vector representing the addition of the two Vectors.
     */

    public Vector addition(Vector add){
        return new Vector(triplet.addition(add.getTriplet()));
    }
    /**
     * Subtracts two Vectors and returns the result as a new Vector.
     *
     * @param substract is the Vector to subtract from this Vector.
     * @return A Vector representing the subtraction between the two Vectors.
     */

    public Vector substraction(Vector substract) {
        return new Vector(triplet.substraction(substract.getTriplet()));
    }
    /**
     * Make a scalar multiplication between a Vector a double and returns the result as a new Vector.
     *
     * @param d is the double given to multiply on this Vector.
     * @return A new Vector representing the scalar multiplication of the Vector and the double.
     */


    public Vector scalarMultiplication(double d) {
        return new Vector(triplet.scalarMultiplication(d));
    }
    /**
     * Make a scalar product between two Vectors and returns the result as a double.
     *
     * @param product is the Vector given to multiply on this Vector.
     * @return A double representing the scalar product of the two Vectors.
     */

    public double scalarProduct(Vector product) {
        return (triplet.scalarProduct(product.getTriplet()));
    }
    /**
     * Make a vector product between two Vectors and returns the result as a new Vector.
     *
     * @param product is the Vector given to multiply on this Vector.
     * @return A Vector representing the vector product of the two Vectors.
     */

    public Vector vectorProduct(Vector product) {
        return new Vector(triplet.vectorProduct(product.getTriplet()));
    }
    /**
     * Calculate the length of a Vector as double.
     *
     * @return A double representing the length of the Vector.
     */

    public double length() {
        return triplet.length();
    }

    /**
     * Calculate the norm of a Vector as a new Vector.
     *
     * @return A Vector representing the norm of this Vector.
     */

    public Vector norm() {
        return new Vector(triplet.norm());
    }
    /**
     * Returns a String of the values in the Vector
     *
     * @return A String representing the string values as coordinates of this Vector.
     */

    @Override
    public String toString() {
        return this.triplet.toString();
    }
}