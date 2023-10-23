package fr.univartois.raytracing.numeric;

public class Triplet {
    //attributes in final because we create a new triplet everytime, so they will not be modified
    protected final double x,y,z;


    public Triplet(double x, double y, double z) { //constructor
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //only getters because attributes are in final
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }



    public Triplet addition (Triplet triplet){
        //following maths formulas, the addition returns a new triplet as (x1+x2,y1+y2,z1+z2)
        return new Triplet(
                this.x+triplet.getX(), this.y+triplet.getY(),this.z+triplet.getZ());

    }
    public Triplet substraction (Triplet triplet){
        //following maths formulas, the substraction returns a new triplet as (x1-x2,y1-y2,z1-z2)
        return new Triplet(
                this.x-triplet.getX(), this.y-triplet.getY(),this.z-triplet.getZ());

    }
    public Triplet scalarMultiplication (double d){
        //following maths formulas, the scalar multiplication returns a new triplet as (d × (x,y,z))
        return new Triplet(this.x*d, this.y*d,this.z*d);

    }

    public double scalarProduct (Triplet product){
        //following maths formulas, the scalar Product returns a double as (x1*x2+y1*y2+z1*z2)
        return (this.x*product.getX()+ this.y* product.getY()+this.z*product.getZ());
    }

    public Triplet vectorProduct (Triplet product){
        //following maths formulas, the vector Product returns a new Triplet as (y1×z2−z1×y2,z1×x2−x1×z2,x1×y2−y1×x2)

        return new Triplet(
                this.y*product.getZ()-this.z*product.getY(),
                this.z*product.getX()-this.x*product.getZ(),
                this.x*product.getY()-this.y*product.getX());
    }

    public Triplet schurProduct (Triplet product){
        //following maths formulas, the Schur Product returns a double as (x1 × x2, y1 × y2, z1 × z2)

        return new Triplet(this.x*product.getX(), this.y* product.getY(),this.z*product.getZ());
    }
    public double length (){
        //following maths formulas, the length returns a double as √(x1×x1 + y1×y1 + z1×z1)
        return (Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z));
    }
    public Triplet norm (){
        //following maths formulas, the length returns a double as (1/√(x1×x1 + y1×y1 + z1×z1))*(x1,y1,z1)
        //we used scalarMultiplication with d as 1/this.length that equals the formula above
        return (this.scalarMultiplication(1/this.length()));
    }


}
