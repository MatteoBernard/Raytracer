package fr.univartois.raytracing.numeric;

public class Point {

    protected Triplet triplet;
    protected double x,y,z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.triplet = new Triplet(x,y,z);
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

    public Triplet substraction(){
        return triplet.substraction(triplet);
    }

    public Triplet scalarMultiplication(double d){
        return triplet.scalarMultiplication(d);
    }
}

