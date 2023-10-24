package fr.univartois.raytracing.numeric;

public class Vector {

    protected final Triplet triplet;

    public Vector(Triplet vector) {
        this.triplet = vector;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public Point addition(Point add) {
        return new Point(triplet.addition(add.getTriplet()));
    }

    public Vector addition(Vector add){
        return new Vector(triplet.addition(add.getTriplet()));
    }

    public Vector substraction(Vector substract) {
        return new Vector(triplet.substraction(substract.getTriplet()));
    }

    public Vector scalarMultiplication(double d) {
        return new Vector(triplet.scalarMultiplication(d));
    }

    public double scalarProduct(Vector product) {
        return (triplet.scalarProduct(product.getTriplet()));
    }

    public Vector vectorProduct(Vector product) {
        return new Vector(triplet.vectorProduct(product.getTriplet()));
    }



    public double length() {
        return triplet.length();
    }

    public Vector norm() {
        return new Vector(triplet.norm());
    }
}