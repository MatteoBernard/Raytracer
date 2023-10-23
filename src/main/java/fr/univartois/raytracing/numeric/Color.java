package fr.univartois.raytracing.numeric;

public class Color {
    protected Triplet triplet;

    public Color(Triplet color) {
        this.triplet = color;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public void setTriplet(Triplet triplet) {
        this.triplet = triplet;
    }

    public Color addition(Triplet add) {
        return new Color(triplet.addition(add));
    }

    public Color scalarMultiplication(double d) {
        return new Color(triplet.scalarMultiplication(d));
    }

    public Color schurProduct(Triplet product) {
        return new Color(triplet.schurProduct(product));
    }
}
