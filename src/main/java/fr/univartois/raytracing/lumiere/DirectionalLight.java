package fr.univartois.raytracing.lumiere;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;

public class DirectionalLight implements ILight{
    Color color;
    Point coord;

    public DirectionalLight(Color color, Point coord) {
        this.color = color;
        this.coord = coord;
    }

    @Override
    public Point getCoord() {
        return this.coord;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
