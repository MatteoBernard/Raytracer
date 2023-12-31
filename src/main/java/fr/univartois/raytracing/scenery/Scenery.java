package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.colors.Checker;
import fr.univartois.raytracing.antiCrenelage.ICrenelage;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.shadow.ShadowState;
import fr.univartois.raytracing.shape.IShape;

import java.util.List;

/**
 * The Scenery class represents the environment in the ray tracing system. It has a camera, a list of lights,
 * a list of shapes, and dimensions for rendering (x and y).
 */
public class Scenery {
    private Camera camera;
    private List<ILight> lights;
    private List<IShape> shapes;
    private int x;
    private int y;
    private ShadowState shadowState;
    private Color ambient;
    private Checker checker;
    private int[] state;

    public int[] getState() {return state;}

    /**
     * Constructs a new Scenery object with the given camera, lights, shapes, rendering dimensions, shadow state, and ambient color.
     *
     * @param camera      The camera used to capture the scene.
     * @param lights      The list of light sources in the scene.
     * @param shapes      The list of geometric shapes in the scene.
     * @param x           The horizontal rendering dimension.
     * @param y           The vertical rendering dimension.
     * @param shadowState The state of shadows in the scene (ShadowState).
     * @param ambient     The ambient color in the scene (Color).
     */

    public Scenery(Camera camera, List<ILight> lights, List<IShape> shapes, int x, int y, ShadowState shadowState, Color ambient, int[] state, Checker checker) {
        this.camera = camera;
        this.lights = lights;
        this.shapes = shapes;
        this.x = x;
        this.y = y;
        this.shadowState = shadowState;
        this.ambient = ambient;
        this.checker = checker;
        this.actualizeChecker();
        this.state = state;
        this.checker = checker;
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

    /**
     * Get the state of shadows in the scene.
     *
     * @return The state of shadows (ShadowState) in the scene.
     */
    public ShadowState getShadowState() {
        return shadowState;
    }

    /**
     * Get the ambient color in the scene.
     *
     * @return The ambient color (Color) in the scene.
     */
    public Color getAmbient() {
        return ambient;
    }

    /**
     * Updates the Checker (if not null) to associate it with the current scene.
     * This is useful for ensuring that the Checker uses the correct scene for calculations.
     */
    public void actualizeChecker () {
        if (checker != null) this.checker.setScene(this);
    }

    /**
     * Retrieves the Checker associated with the scene.
     *
     * @return The Checker object associated with the scene, or null if no Checker is set.
     */
    public Checker getChecker() {
        return checker;
    }
}
