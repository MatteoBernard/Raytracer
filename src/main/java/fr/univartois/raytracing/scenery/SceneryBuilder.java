package fr.univartois.raytracing.scenery;


import fr.univartois.raytracing.colors.Checker;
import fr.univartois.raytracing.antiCrenelage.ICrenelage;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.shadow.ShadowOFF;
import fr.univartois.raytracing.shadow.ShadowState;
import fr.univartois.raytracing.shape.IShape;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Builder interface and is responsible for building the scene
 * by managing the collection of shapes and lights.
 */
public class SceneryBuilder implements Builder {
    private Camera camera;
    private List<ILight> lights;
    private List<IShape> shapes;
    private ShadowState shadowState;
    private Color ambient;
    private int x;
    private int y;
    private Checker checker;
    private int[] state;

    /**
     * Constructs a new SceneryBuilder with the specified horizontal and vertical rendering dimensions (x and y).
     *
     * @param x The horizontal rendering dimension.
     * @param y The vertical rendering dimension.
     */
    public SceneryBuilder(int x, int y) {
        this.x = x;
        this.y = y;
        this.shapes = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.shadowState = ShadowOFF.getInstance();
    }

    /**
     * Sets the camera for the scenery builder. The camera is used to capture the scene.
     *
     * @param camera The Camera object to set for the scene.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Sets the checker for the scenery.
     *
     * @param checker The Checker object to set for the scene.
     */
    public void setChecker(Checker checker){this.checker = checker;}
    /**
     * Sets the list of shapes for the scenery.
     *
     * @param shapes The list of shapes to be part of the scene.
     */
    @Override
    public void setShapes(List<IShape> shapes) {
        this.shapes = shapes;
    }

    /**
     * Adds a shape to the list of shapes in the scenery.
     *
     * @param shape The shape to add to the scene.
     */
    @Override
    public void addShape(IShape shape) {
        this.shapes.add(shape);
    }

    /**
     * Sets the list of lights for the scenery.
     *
     * @param lights The list of lights to illuminate the scene.
     */
    @Override
    public void setLight(List<ILight> lights) {
        this.lights = lights;
    }

    /**
     * Adds a light to the list of lights in the scenery.
     *
     * @param light The light source to add to the scene.
     */
    @Override
    public void addLight(ILight light) {
        this.lights.add(light);
    }

    /**
     * Retrieves the list of shapes in the scenery.
     *
     * @return The list of shapes in the scene.
     */
    public List<IShape> getShapes() {
        return shapes;
    }

    /**
     * Retrieves the list of lights in the scenery.
     *
     * @return The list of lights in the scene.
     */
    public List<ILight> getLights() {
        return lights;
    }

    /**
     * Retrieves the camera object in the scenery.
     *
     * @return The camera object in the scene.
     */
    public Camera getCamera() {
        return this.camera;
    }

    /**
     * Retrieves the horizontal rendering dimension (x).
     *
     * @return The horizontal rendering dimension.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the horizontal rendering dimension (x).
     *
     * @param x The horizontal rendering dimension to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the vertical rendering dimension (y).
     *
     * @return The vertical rendering dimension.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the vertical rendering dimension (y).
     *
     * @param y The vertical rendering dimension to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the shadow state for the scenery.
     *
     * @param shadowState The shadow state to set for the scene (ShadowState).
     */
    public void setShadowState(ShadowState shadowState) {
        this.shadowState = shadowState;
    }

    /**
     * Retrieves the shadow state of the scenery.
     *
     * @return The current shadow state of the scene (ShadowState).
     */
    public ShadowState getShadowState() {
        return shadowState;
    }

    /**
     * Sets the ambient color for the scenery.
     *
     * @param ambient The ambient color to set for the scene (Color).
     */
    public void setAmbient(Color ambient) {
        this.ambient = ambient;
    }

    /**
     * Retrieves the ambient color of the scenery.
     *
     * @return The ambient color in the scene (Color).
     */
    public Color getAmbient() {
        return ambient;
    }

    /**
     * Retrieves the checker of the scenery.
     *
     * @return The checker in the scene (Checker).
     */
    public Checker getChecker() {
        return checker;
    }

    /**
     * Retrieves the current state of the antialiasing
     *
     * @return An array of integers representing the state.
     */
    public int[] getState() {return state;}

    /**
     * Sets the state using a given array of integers.
     *
     * @param state The array of integers to use as the new state of the antialiasing.
     */
    public void setState(int[] state) {this.state=state;}
}

