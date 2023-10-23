package fr.univartois.raytracing.numeric;

public class Vector {

    protected Triplet triplet;

    public Vector(Triplet vector) {
        this.triplet = vector;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public void setTriplet(Triplet triplet) {
        this.triplet = triplet;
    }

    public Point addition(Triplet add) {
        return new Point(triplet.addition(add));
    }

    public Vector substraction(Triplet substract) {
        return new Vector(triplet.substraction(substract));
    }

    public Point scalarMultiplication(double d) {
        return new Point(triplet.scalarMultiplication(d));
    }

    public double scalarProduct(Triplet product) {
        return (triplet.scalarProduct(product));
    }

    public Triplet vectorProduct(Triplet product) {
        return (triplet.vectorProduct(product));
    }



    public double length() {
        return triplet.length();
    }

    public Triplet norm(Triplet product) {
        return (triplet.norm(product));
    }
}