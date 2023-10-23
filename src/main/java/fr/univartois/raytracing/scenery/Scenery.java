package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.ArrayList;

public class Scenery {
    Camera camera;
    ArrayList<ILight> lights;
    ArrayList<IShape> shapes;

    int x;
    int y;

    public Scenery(Camera camera, ArrayList<ILight> lights, ArrayList<IShape> shapes, int x, int y) {
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

    public ArrayList<ILight> getLights() {
        return lights;
    }

    public void setLights(ArrayList<ILight> lights) {
        this.lights = lights;
    }

    public ArrayList<IShape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<IShape> shapes) {
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
