package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.shadow.ShadowOFF;
import fr.univartois.raytracing.shadow.ShadowState;
import fr.univartois.raytracing.shape.IShape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class implements the Builder interface and is responsible for building the scene
 * by managing the collection of shapes and lights.
 */
public class SceneryBuilder implements Builder {
    private Camera camera;
    private List<ILight> lights;
    private List<IShape> shapes;
    private HashMap<String,Color> colors;
    private ShadowState shadowState;
    private int x;
    private int y;

    public SceneryBuilder(int x, int y) {
        this.x = x;
        this.y = y;
        this.shapes = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.shadowState = ShadowOFF.getInstance();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Sets list of shapes for the scenery.
     *
     * @param shapes list of shapes to be part of the scene.
     */
    @Override
    public void setShapes(List<IShape> shapes) {
        this.shapes = shapes;
    }

    /**
     * Adds a shape to the list of shapes in the scenery.
     *
     * @param shape shape to add to the scene.
     */
    @Override
    public void addShape(IShape shape) {
        this.shapes.add(shape);
    }

    /**
     * Sets list of lights for the scenery.
     *
     * @param lights list of lights to illuminate the scene.
     */
    @Override
    public void setLight(List<ILight> lights) {
        this.lights = lights;
    }

    /**
     * Adds a light to the list of lights in the scenery.
     *
     * @param light light source to add to the scene.
     */
    @Override
    public void addLight(ILight light) {
        this.lights.add(light);
    }

    /**
     * Retrieves list of shapes in the scenery.
     *
     * @return list of shapes in the scene.
     */
    public List<IShape> getShapes() {
        return shapes;
    }

    /**
     * Retrieves list of lights in the scenery.
     *
     * @return list of lights in the scene.
     */
    public List<ILight> getLights() {
        return lights;
    }

    public Camera getCamera() {
        return this.camera;

    }

    public void setLights(List<ILight> lights) {
        this.lights = lights;
    }

    public HashMap<String, Color> getColors() {
        return this.colors;
    }

    public void setColors(HashMap<String, Color> colors) {
        this.colors = colors;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShadowState(ShadowState shadowState) {
        this.shadowState = shadowState;
    }

    public ShadowState getShadowState() {
        return shadowState;
    }
}
