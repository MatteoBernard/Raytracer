package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.ArrayList;
import java.util.List;

public class Scenery {
    Camera camera;
    List<ILight> lights;
    List<IShape> shapes;

    int x;
    int y;

    public Scenery(Camera camera, List<ILight> lights, List<IShape> shapes, int x, int y) {
        this.camera = camera;
        this.lights = lights;
        this.shapes = shapes;
        this.x = x;
        this.y = y;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<ILight> getLights() {
        return lights;
    }

    public void setLights(List<ILight> lights) {
        this.lights = lights;
    }

    public List<IShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<IShape> shapes) {
        this.shapes = shapes;
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
}
