/**
 * The Triplet class represents a triplet of values as coordinates that can become Colors(r,g,b) and stuff.
 * This class provides mathematical operations that you can apply on the triplets.
 *
 * @author antoine_crauser
 */

package fr.univartois.raytracing.numeric;

public class Triplet {
    //attributes in final because we create a new triplet everytime, so they will not be modified
    /**
     * The coordinate x of the triplet.
     */
    protected final double x;
    /**
     * The coordinate y of the triplet.
     */
    protected final double y;
    /**
     * The coordinate z of the triplet.
     */
    protected final double z;

    /**
     * This constructor constructs a new Triplet with the specified values as coordinates.
     *
     * @param x The coordinate x.
     * @param y The coordinate y.
     * @param z The coordinate z.
     */

    public Triplet(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Returns the coordinate x of the triplet.
     *
     * @return The coordinate x.
     */

    //only getters since attributes are in final
    public double getX() {
        return this.x;
    }

    /**
     * Returns the coordinate y of the triplet.
     *
     * @return The coordinate y.
     */

    public double getY() {return this.y;}

    /**
     * Returns the coordinate z of the triplet.
     *
     * @return The coordinate z.
     */

    public double getZ() {
        return this.z;
    }

    /**
     * Make an addition between a Triplet and another Triplet and returns the result as a new Triplet.
     *
     * @param add is the Triplet given to add to this Triplet.
     * @return A new Triplet representing the addition of the two triplets.
     */

    public Triplet addition (Triplet add){
        //following maths formulas, the addition returns a new triplet as (x1+x2,y1+y2,z1+z2)
        return new Triplet(
                this.x+add.getX(), this.y+add.getY(),this.z+add.getZ());
    }

    /**
     * Make a subtraction between a Triplet and another Triplet and returns the result as a new Triplet.
     *
     * @param triplet is the Triplet given to substract on this Triplet.
     * @return A new Triplet representing the subtraction of the two triplets.
     */

    public Triplet substraction (Triplet triplet){
        //following maths formulas, the substraction returns a new triplet as (x1-x2,y1-y2,z1-z2)
        return new Triplet(
                this.x-triplet.getX(), this.y-triplet.getY(),this.z-triplet.getZ());

    }
    /**
     * Make a scalar multiplication between a Triplet a double and returns the result as a new Triplet.
     *
     * @param d is the double given to multiply on this Triplet.
     * @return A new Triplet representing the scalar multiplication of the triplet and the double.
     */

    public Triplet scalarMultiplication (double d){
        //following maths formulas, the scalar multiplication returns a new triplet as (d × (x,y,z))
        return new Triplet(this.x*d, this.y*d,this.z*d);
    }

    /**
     * Make a scalar product between two Triplets and returns the result as a double.
     *
     * @param product is the Triplet given to multiply on this Triplet.
     * @return A double representing the scalar product of the two triplets.
     */

    public double scalarProduct (Triplet product){
        //following maths formulas, the scalar Product returns a double as (x1*x2+y1*y2+z1*z2)
        return (this.x*product.getX()+ this.y* product.getY()+this.z*product.getZ());
    }

    /**
     * Make a vector product between two Triplets and returns the result as a new Triplet.
     *
     * @param product is the Triplet given to multiply on this Triplet.
     * @return A Triplet representing the vector product of the two triplets.
     */

    public Triplet vectorProduct (Triplet product){
        //following maths formulas, the vector Product returns a new Triplet as (y1×z2−z1×y2,z1×x2−x1×z2,x1×y2−y1×x2)

        return new Triplet(
                this.y*product.getZ()-this.z*product.getY(),
                this.z*product.getX()-this.x*product.getZ(),
                this.x*product.getY()-this.y*product.getX());
    }

    /**
     * Make a schur product between two Triplets and returns the result as a new Triplet.
     *
     * @param product is the Triplet given to multiply on this Triplet.
     * @return A Triplet representing the schur product of the two triplets.
     */

    public Triplet schurProduct (Triplet product){
        //following maths formulas, the Schur Product returns a Triplet as (x1 × x2, y1 × y2, z1 × z2)

        return new Triplet(this.x*product.getX(), this.y* product.getY(),this.z*product.getZ());
    }

    /**
     * Calculate the length of a Triplet as double.
     *
     * @return A double representing the length of the Triplet.
     */

    public double length (){
        //following maths formulas, the length returns a double as √(x1×x1 + y1×y1 + z1×z1)
        return (Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z));
    }

    /**
     * Calculate the norm of a Triplet as a new Triplet.
     *
     * @return A Triplet representing the norm of this Triplet.
     */
    public Triplet norm (){
        //following maths formulas, the length returns a double as (1/√(x1×x1 + y1×y1 + z1×z1))*(x1,y1,z1)
        //we used scalarMultiplication with d as 1/this.length that equals the formula above
        return (this.scalarMultiplication(1/this.length()));
    }
    /**
     * Returns a String of the values in the Triplet
     *
     * @return A Triplet representing the norm of this Triplet.
     */
    @Override
    public String toString() {
        return "X : "+this.getX()+";  Y : "+this.getY()+";  Z : "+this.getZ();
    }
}
