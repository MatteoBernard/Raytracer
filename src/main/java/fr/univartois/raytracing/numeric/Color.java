package fr.univartois.raytracing.numeric;

public class Color {
    protected final Triplet triplet;

    public Color(Triplet color) {
        this.triplet = color;
    }

    public Triplet getTriplet() {
        return this.triplet;
    }

    public Color addition(Color add) {
       // new
        return new Color(triplet.addition(add.getTriplet()));
    }

    public Color scalarMultiplication(double d) {
        return new Color(triplet.scalarMultiplication(d));
    }

    public Color schurProduct(Color product) {
        return new Color(triplet.schurProduct(product.getTriplet()));
    }
}
