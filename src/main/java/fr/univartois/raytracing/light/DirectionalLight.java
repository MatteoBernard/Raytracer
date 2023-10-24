package fr.univartois.raytracing.light;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * The DirectionalLight class represents a directional light source in a ray tracing system. It emits light
 * of a specific color and is defined by a direction vector.
 */
public class DirectionalLight implements ILight {
    private Color color;
    private Vector vector;

    /**
     * Constructs a new DirectionalLight object with the specified color and direction vector.
     *
     * @param color  color of the directional light.
     * @param vector direction vector of the light.
     */
    public DirectionalLight(Color color, Vector vector) {
        this.color = color;
        this.vector = vector;
    }

    /**
     * Get the position of the directional light (null for directional lights).
     *
     * @return position of the light (null for directional lights).
     */
    @Override
    public Point getCoord() {
        return null;
    }

    /**
     * Get the direction vector of the light.
     *
     * @return direction vector of the light source.
     */
    @Override
    public Vector getVector() {
        return this.vector;
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
