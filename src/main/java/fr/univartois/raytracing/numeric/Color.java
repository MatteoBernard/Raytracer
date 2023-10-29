/**
 * The Color class represents a triplet of values using a Triplet.
 * This class provides mathematical operations that you can apply on the Color.
 *
 * @author antoine_crauser
 */
package fr.univartois.raytracing.numeric;

public class Color {

    /**
     * This is the triplet representing the Color.
     */
    protected final Triplet triplet;
    /**
     * This constructor constructs a new Color using a Triplet.
     *
     * @param color The Triplet that defines the r,g,b of the Color.
     */
    public Color(Triplet color) {
        this.triplet = color;
    }
    /**
     * Returns the Triplet of the Color.
     *
     * @return The Color.
     */

    public Triplet getTriplet() {
        return this.triplet;
    }
    /**
     * Make an addition between a Color and another Color and returns the result as a new Color.
     *
     * @param add is the Color given to add to this Color.
     * @return A new Color representing the addition of the two Colors.
     */

    public Color addition(Color add) {
       // new
        return new Color(this.triplet.addition(add.getTriplet()));
    }
    /**
     * Make a scalar multiplication between a Color a double and returns the result as a new Color.
     *
     * @param d is the double given to multiply on this Color.
     * @return A new Color representing the scalar multiplication of the color and the double.
     */

    public Color scalarMultiplication(double d) {
        return new Color(triplet.scalarMultiplication(d));
    }
    /**
     * Make a schur product between two Colors and returns the result as a new Color.
     *
     * @param product is the Color given to multiply on this Color.
     * @return A Color representing the schur product of the two Colors.
     */


    public Color schurProduct(Color product) {
        return new Color(triplet.schurProduct(product.getTriplet()));
    }

    /**
     * Returns a String of the values in the Color as a triplet
     *
     * @return A String representing the string values r,g,b of this Color.
     */
    @Override
    public String toString() {
        return this.triplet.toString();
    }
}
