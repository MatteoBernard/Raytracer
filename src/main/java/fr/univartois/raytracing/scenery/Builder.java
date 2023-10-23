package fr.univartois.raytracing.scenery;

import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.shape.IShape;

import java.util.List;

public interface Builder {
    void setShapes(List<IShape> shapes);
    void addShape(IShape shape);
    void setLight(List<ILight> lights);
    void addLight(ILight light);

}
