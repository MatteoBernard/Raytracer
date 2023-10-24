package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.HashMap;
import java.util.List;

/**
 * The Scenery class represents the environment in a ray tracing system. It has a camera, a list of lights,
 * a list of shapes, and dimensions for rendering (x and y).
 */
public class Scenery {
    private Camera camera;
    private List<ILight> lights;
    private List<IShape> shapes;
    private HashMap<String,Color> colors;
    private int x;
    private int y;

    /**
     * Constructs a new Scenery object with the given camera, lights, shapes, and rendering dimensions.
     *
     * @param camera camera used to capture the scene.
     * @param lights list of light sources in the scene.
     * @param shapes list of geometric shapes in the scene.
     * @param colors
     * @param x      horizontal rendering dimension.
     * @param y      vertical rendering dimension.
     */
    public Scenery(Camera camera, List<ILight> lights, List<IShape> shapes, HashMap<String, Color> colors, int x, int y) {
        this.camera = camera;
        this.lights = lights;
        this.shapes = shapes;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the camera used in the scene.
     *
     * @return camera object.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Set the camera for the scene.
     *
     * @param camera camera object to set.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Get the list of light sources in the scene.
     *
     * @return list of light sources.
     */
    public List<ILight> getLights() {
        return lights;
    }

    /**
     * Set the list of light sources for the scene.
     *
     * @param lights list of light sources to set.
     */
    public void setLights(List<ILight> lights) {
        this.lights = lights;
    }

    /**
     * Get the list of geometric shapes in the scene.
     *
     * @return list of shapes in the scene.
     */
    public List<IShape> getShapes() {
        return shapes;
    }

    /**
     * Set the list of geometric shapes for the scene.
     *
     * @param shapes list of shapes to set.
     */
    public void setShapes(List<IShape> shapes) {
        this.shapes = shapes;
    }

    /**
     * Get the horizontal rendering dimension (x).
     *
     * @return horizontal rendering dimension.
     */
    public int getX() {
        return x;
    }

    /**
     * Set the horizontal rendering dimension (x).
     *
     * @param x horizontal rendering dimension to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the vertical rendering dimension (y).
     *
     * @return vertical rendering dimension.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the vertical rendering dimension (y).
     *
     * @param y vertical rendering dimension to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    public HashMap<String, Color> getColors() {
        return  this.colors;
    }
}
