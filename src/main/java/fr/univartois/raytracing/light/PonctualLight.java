package fr.univartois.raytracing.light;


import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * The PonctualLight class represents a ponctual light source in a ray tracing system. It emits light
 * of a specific color and is defined by a direction vector.
 */
public class PonctualLight implements ILight{
    Color color; // color of the light
    Point coord; // the coordonate of the light


    /**
     * Constructs a new DirectionalLight object with the specified color and direction vector.
     *
     * @param color  color of the directional light.
     * @param coord  coord of the light.
     */
    public PonctualLight(Color color, Point coord) {
        this.color = color;
        this.coord = coord;
    }

    /**
     * Get the position of the light (null for directional lights).
     *
     * @return position of the light (null for directional lights).
     */
    @Override
    public Vector getVector() {
        return null;
    }

    /**
     * Get the direction vector of the light.
     *
     * @return direction vector of the light source.
     */
    @Override
    public Point getCoord() {
        return this.coord;
    }

    /**
     * Get the color of the light.
     *
     * @return color of the light source.
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns a string representation of this PonctualLight object.
     *
     * @return A string containing the class name, color, and vector of the PonctualLight.
     */
    @Override
    public String toString() {
        return "PonctualLight{" +
                "color=" + color +
                ", coord=" + coord +
                '}';
    }
}
