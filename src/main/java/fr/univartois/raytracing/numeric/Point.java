package fr.univartois.raytracing.numeric;

public class Point {

    protected final Triplet triplet; //we use an instance of Triplet also in final

    /**
     *
     * @param point
     */
    public Point(Triplet point) { //constructor
        this.triplet=point;
    }

    /**
     *
     * @return
     */
    public Triplet getTriplet() {//getter
        return this.triplet;
    }

    /**
     *
     * @param substract
     * @return
     */
    public Vector substraction(Point substract){//we use the substraction from Triplet with an instance of Point
        return new Vector(triplet.substraction(substract.getTriplet()));
    }

    /**
     *
     * @param d
     * @return
     */
    public Point scalarMultiplication(double d){ // we use the scalar Multiplication from Triplet but it returns a Point
        return new Point(triplet.scalarMultiplication(d));
    }
}

