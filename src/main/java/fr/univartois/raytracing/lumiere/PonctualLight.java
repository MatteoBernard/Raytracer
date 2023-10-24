package fr.univartois.raytracing.lumiere;


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
     * Constructs a new PonctualLight object with the specified color and direction vector.
     *
     * @param color  color of the directional light.
     * @param coord  coord of the light.
     */
    public PonctualLight(Color color, Point coord) {
        this.color = color;
        this.vector = vector;
    }

    /**
     * Get the position of the light (null for directional lights).
     *
     * @return position of the light (null for directional lights).
     */
    @Override
    public Vector getVector() {
        return this.vector;
    }

    /**
     * Get the direction vector of the light.
     *
     * @return direction vector of the light source.
     */
    @Override
    public Vector getVector() {
        return null;
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
}
