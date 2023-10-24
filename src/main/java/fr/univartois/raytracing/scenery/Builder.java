package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.List;

/**
 * The Builder interface provides a contract for classes responsible for building and configuring scenes
 * in a ray tracing system. Implementing classes must define methods to set and add shapes and lights to the scene.
 */
public interface Builder {
    /**
     * Sets the list of shapes for the scene.
     *
     * @param shapes list of shapes to be part of the scene.
     */
    void setShapes(List<IShape> shapes);

    /**
     * Adds a shape to the list of shapes in the scene.
     *
     * @param shape shape to add to the scene.
     */
    void addShape(IShape shape);

    /**
     * Sets the list of lights for the scene.
     *
     * @param lights list of lights to illuminate the scene.
     */
    void setLight(List<ILight> lights);

    /**
     * Adds a light to the list of lights in the scene.
     *
     * @param light light source to add to the scene.
     */
    void addLight(ILight light);
}
