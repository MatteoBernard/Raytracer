package fr.univartois.raytracing.lumiere;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;

public class DirectionalLight implements ILight{
    Vector vector;
    Color color;

    public DirectionalLight(Vector vector, Color color) {
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
