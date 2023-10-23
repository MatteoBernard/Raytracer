package fr.univartois.raytracing.lumiere;

public class DirectionalLight implements ILight{
    Color color;
    Point coord;

    public DirectionalLight(Color color, Triplet coord) {
        this.color = color;
        this.coord = coord;
    }

    @Override
    public Triplet getCoord() {
        return this.coord;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
