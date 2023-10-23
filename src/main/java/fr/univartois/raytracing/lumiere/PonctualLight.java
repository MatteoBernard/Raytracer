package fr.univartois.raytracing.lumiere;


import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

public class PonctualLight implements ILight{
    Color color;
    Vector vector;

    public PonctualLight(Color color, Vector vector) {
        this.color = color;
        this.vector = vector;
    }

    @Override
    public Vector getVector() {
        return this.vector;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
