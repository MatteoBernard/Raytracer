package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.List;

public class SceneryBuilder implements Builder{
    private List<IShape> shapes;
    private List<ILight> lights;


    @Override
    public void setShapes(List<IShape> shapes) {
        this.shapes=shapes;
    }

    @Override
    public void addShape(IShape shape) {
        this.shapes.add(shape);

    }

    @Override
    public void setLight(List<ILight> lights) {
        this.lights=lights;

    }

    @Override
    public void addLight(ILight light) {
        this.lights.add(light);

    }

    public List<IShape> getShapes() {
        return shapes;
    }

    public List<ILight> getLights() {
        return lights;
    }

}
