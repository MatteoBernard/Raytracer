package fr.univartois.raytracing.numeric;

public class Point {

    protected final Triplet triplet;


    public Point(Triplet point) {
        this.triplet=point;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public Vector substraction(Point substract){
        return new Vector(triplet.substraction(substract.getTriplet()));
    }

    public Point scalarMultiplication(double d){
        return new Point(triplet.scalarMultiplication(d));
    }
}

