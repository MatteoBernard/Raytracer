package fr.univartois.raytracing.numericTriplets;

public class Triplet {
    protected double x,y,z;

    public Triplet(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    protected Triplet addition (Triplet triplet){
        return new Triplet(
                triplet.getX()+this.x, triplet.getY()+this.y,triplet.getZ()+this.z);

    }
    protected Triplet substraction (Triplet triplet){
        return new Triplet(
                triplet.getX()-this.x, triplet.getY()-this.y,triplet.getZ()-this.z);

    }
    protected Triplet scalarMultiplication (double d){
        return new Triplet(this.x*d, this.y*d,this.z*d);

    }

    protected double scalarProduct (Triplet product){
        return (this.x*product.getX()+ this.y* product.getY()+this.z*product.getZ());
    }

    protected Triplet vectorProduct (Triplet product){
        return new Triplet(
                this.y*product.getZ()-this.z*product.getY(),
                this.z*product.getX()-this.x*product.getZ(),
                this.x*product.getY()-this.y*product.getX());
    }

    protected Triplet schurProduct (Triplet product){
        return new Triplet(this.x*product.getX(), this.y* product.getY(),this.z*product.getZ());
    }
    protected double length (){
        return (Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z));
    }
    protected Triplet norm (Triplet product){
        return (this.scalarMultiplication(1/this.length()));
    }


}
