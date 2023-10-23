package fr.univartois.raytracing.numeric;

public class Point {

    protected Triplet triplet;


    public Point(Triplet point) {
        this.triplet=point;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public void setTriplet(Triplet triplet) {
        this.triplet = triplet;
    }

    public Vector substraction(Triplet substract){
        return new Vector(triplet.substraction(substract));
    }

    public Point scalarMultiplication(double d){
        return new Point(triplet.scalarMultiplication(d));
    }
}

