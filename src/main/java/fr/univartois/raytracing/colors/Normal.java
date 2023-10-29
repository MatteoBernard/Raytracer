/**
 * The Normal class is used for the ambient of the light.
 * It calculates the color of objects using the ambient light.
 *
 * @author antoine_crauser
 * @version 1.0
 */
package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

public class Normal implements ICalcul {
    private final Color amb;
    private Scenery scene;

    /**
     * Return the ambient light color.
     *
     * @return The ambient color for an objects.
     */

    public Color getAmb() {
        return this.amb;
    }

    /**
     * This constructor constructs an object of Normal for calculations.
     *
     * @param scene The scene on which the ambient will apply.
     */

    public Normal(Scenery scene) {
        this.scene=scene;
        this.amb = scene.getAmbient();
    }

    /**
     * Use the ambient light for colors shades.
     *
     * @param shape is the shape on which we apply the shading.
     * @param d is the vector that allow to calculate the intersection point with the shape.
     * @param t t is the double used for calculations.
     * @return The ambient shape color.
     */

    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {
        return this.getAmb();
    }

    /**
     * Returns the Scenery of the Normal object.
     *
     * @return The scene to calculate the shading .
     */

    @Override
    public Scenery getScene() {
        return this.scene;
    }
}