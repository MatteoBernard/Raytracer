package fr.univartois.raytracing.light;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * The ILight interface defines the contract for all types of light sources in a ray tracing system.
 */
public interface ILight {

    /**
     * Get the direction vector of the light source.
     *
     * @return direction vector representing the light's direction.
     */
    Vector getVector();

    /**
     * Get the color of the light source.
     *
     * @return color of the light.
     */
    Color getColor();

    /**
     * Get the position of the light source (if applicable, otherwise return null).
     *
     * @return position of the light source (or null if the light has no specific position).
     */
    Point getCoord();
}
